package de.juliansoftware.group;

import de.juliansoftware.constants.Constants;
import dev.brokenstudio.cloud.cloudplugin.CloudPlugin;
import dev.brokenstudio.cloud.database.mariadb.SQLPipe;
import dev.brokenstudio.cloud.serializer.Serializer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GroupHandler {

    private final HashMap<String, Group> groups;

    {
        groups = new HashMap<>();
        _init();
    }

    private void _init(){
        CloudPlugin.api().getDeveloperDatabase().getMariaDBHandler().executeQuery("CREATE TABLE IF NOT EXISTS `tv_groups` (`group_name` VARCHAR(100),`group_data`LONGTEXT);");
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = CloudPlugin.api().getDeveloperDatabase().getMariaDBHandler().getSQLConnection().getDriverConnection();
            rs = connection.createStatement().executeQuery("SELECT * FROM `tv_groups`;");
            while(rs.next()){
                groups.put(rs.getString("group_name"), Serializer.deserialize(rs.getString("group_data"), Group.class));

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

    public Group getGroup(String name){
        return groups.getOrDefault(name, null);
    }

    public void addGroup(String name){
        groups.put(name, new Group(name, "§f"));
    }

    public void removeGroup(String name){
        groups.remove(name);
    }
    public Set<String> getGroups() {
        return groups.keySet();
    }

    public void saveGroups(){
        SQLPipe pipe = new SQLPipe();
        pipe.addQuery("DELETE FROM `tv_groups`;");
        groups.forEach((name, data) -> {
            pipe.addQuery("INSERT INTO `tv_groups` (`group_name`,`group_data`) VALUES ('"+name+"','"+Serializer.serialize(data)+"')");
        });
        CloudPlugin.api().getDeveloperDatabase().getMariaDBHandler().executePipe(pipe);
    }
}
