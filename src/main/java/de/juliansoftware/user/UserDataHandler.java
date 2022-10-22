package de.juliansoftware.user;

import de.juliansoftware.group.Group;
import de.juliansoftware.main.Main;
import dev.brokenstudio.cloud.cloudplugin.CloudPlugin;
import dev.brokenstudio.cloud.database.mariadb.SQLPipe;
import dev.brokenstudio.cloud.serializer.Serializer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class UserDataHandler {

    private final HashMap<UUID,UserData> data;

    {
        data = new HashMap<>();
        _init();
    }

    private void _init(){
        CloudPlugin.api().getDeveloperDatabase().getMariaDBHandler().executeQuery("CREATE TABLE IF NOT EXISTS `tv_users` (`user_id` VARCHAR(100),`user_data`LONGTEXT);");
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = CloudPlugin.api().getDeveloperDatabase().getMariaDBHandler().getSQLConnection().getDriverConnection();
            rs = connection.createStatement().executeQuery("SELECT * FROM `tv_users`;");
            while(rs.next()){
                data.put(UUID.fromString(rs.getString("user_id")), Serializer.deserialize(rs.getString("user_data"), UserData.class));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public UserData getData(UUID uuid){
        return data.getOrDefault(uuid, null);
    }

    public HashMap<UUID, UserData> getData() {
        return data;
    }

    public void removeUser(UUID uuid) {
        data.remove(uuid);
        saveUser();
    }
    public boolean addUser(UUID uuid, String groupName, String name){
        Group group = Main.getInstance().getGroupService().getGroup(groupName);
        if(group == null){
            return false;
        }
        data.put(uuid, new UserData(uuid, group, new Date(), name));
            saveUser();

        return true;
    }

    public void saveUser(){
        SQLPipe pipe = new SQLPipe();
        pipe.addQuery("DELETE FROM `tv_users`;");

        data.forEach((id, data) -> {
            pipe.addQuery("INSERT INTO `tv_users` (`user_id`,`user_data`) VALUES ('"+id.toString()+"','"+Serializer.serialize(data)+"');");
        });
        CloudPlugin.api().getDeveloperDatabase().getMariaDBHandler().executePipe(pipe);
    }
}
