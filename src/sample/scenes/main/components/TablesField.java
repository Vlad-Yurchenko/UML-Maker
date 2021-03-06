package sample.scenes.main.components;

import javafx.scene.Group;
import sample.model.DBField;
import sample.model.DBTable;
import sample.model.DB_TYPE;
import sample.model.Database;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Рабочая область содержащая множество таблиц
 * (размер определяется относительно размера окна)
 */
public class TablesField extends Group {

    private static TablesField instance = new TablesField();

    private Database database;
    private ArrayList<Table> tables;
    private ArrayList<Link> links;

    private TablesField() {
        tables = new ArrayList<>();
        links = new ArrayList<>();
        database = new Database();

        /**
         * тестовые таблицы
         * TODO: убрать их
         * */
        Table table1 = new Table("Users");
        table1.getDBTable().getFields().add(new DBField("id", DB_TYPE.INT, true, true, false, false));
        table1.getDBTable().getFields().add(new DBField("name", DB_TYPE.VARCHAR));
        table1.getDBTable().getFields().add(new DBField("sur_name", DB_TYPE.VARCHAR));

        table1.refresh();

        Table table2 = new Table("Roles");
        table2.getDBTable().getFields().add(new DBField("id", DB_TYPE.INT, true, true, false, false));
        table2.getDBTable().getFields().add(new DBField("name", DB_TYPE.VARCHAR));

        table2.refresh();

        tables.add(table1);
        tables.add(table2);
        getChildren().addAll(table1, table2);
    }

    public static TablesField getInstance() {
        return instance;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public Database getDataBase() {
        ArrayList<DBTable> dbTables = new ArrayList<>();
        tables.forEach(table -> dbTables.add(table.getDBTable()));
        database.setTables(dbTables);
        return database;
    }

    public void addNewTable(String name) {
        Table table = new Table(name);
        tables.add(table);
        getChildren().add(table);
    }

}
