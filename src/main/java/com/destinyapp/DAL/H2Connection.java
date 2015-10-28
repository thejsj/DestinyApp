package com.destinyapp.DAL;

import org.h2.tools.DeleteDbFiles;

import java.sql.*;


public class H2Connection {

    private static final String DB_DRIVER="org.h2.Driver";
    private static final String DB_CONNECTION="jdbc:h2:~/DestinyDB";
    private static final String DB_USER="";
    private static final String DB_PASSWORD="";

    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~","DestinyDB",true);
            createTables();
            //insert data...
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return dbConnection;
    }

    private static void createTables() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt=connection.createStatement();

            //Burn table
            stmt.execute("CREATE TABLE BURN(BurnId number NOT NULL, BurnName varchar2(20), ImageLink varchar2(500))");
            stmt.execute("ALTER TABLE BURN ADD CONSTRAINT BURN_PK PRIMARY KEY (BurnId)");
            stmt.execute("INSERT INTO BURN(BurnId, BurnName, ImageLink) VALUES (1, 'Void', 'http://oyster.ignimgs.com/mediawiki/apis.ign.com/destiny/thumb/f/f8/Void-Grimoire.png/228px-Void-Grimoire.png')");
            stmt.execute("INSERT INTO BURN(BurnId, BurnName, ImageLink) VALUES (2, 'Arc', 'http://oyster.ignimgs.com/mediawiki/apis.ign.com/destiny/thumb/e/ee/Arc-Grimoire.png/228px-Arc-Grimoire.png')");
            stmt.execute("INSERT INTO BURN(BurnId, BurnName, ImageLink) VALUES (3, 'Solar', 'https://www.bungie.net/common/destiny_content/grimoire/hr_images/305030_dd390b16d368059137d8007aea0d511c.jpg')");

            //Class table
            stmt.execute("CREATE TABLE CLASS (ClassId number NOT NULL, ClassName varchar2(20) NOT NULL, Description varchar2(500) NOT NULL, ImageLink varchar2(500))");
            stmt.execute("ALTER TABLE CLASS ADD CONSTRAINT CLASS_PK PRIMARY KEY (ClassId)");
            stmt.execute("INSERT INTO CLASS(ClassId, ClassName, Description, ImageLink) VALUES (1, 'Warlock', 'Warlocks have long studied the Traveler, mastering some of its arcane energies. Its true purpose still remains a great mystery, but discovering truth has always driven you into the unknown. Now, our enemies are the only thing that stands between you and the lost wonders of our Golden Age.', '../Images/warlock_logo.jpg')");
            stmt.execute("INSERT INTO CLASS(ClassId, ClassName, Description, ImageLink) VALUES (2, 'Titan', 'The first Titans built the Wall, and gave their lives to defend it. Now, you stand in the same high place, steadfast and sure, protecting all who shelter in your shadow. You hail from a long line of heroes, forged from strength and sacrifice. Our enemies may be deadly and merciless, but so are you.', '../Images/titan_logo.jpg')");
            stmt.execute("INSERT INTO CLASS(ClassId, ClassName, Description, ImageLink) VALUES (3, 'Hunter', 'Hunters once prowled the wilderness and wastelands, taking big risks for even bigger rewards. Youre no outlaw—at least, not anymore—but making your own luck has always meant bending the rules. Your unique brand of daring and ingenuity is needed now more than ever.', '../Images/hunter_logo.png')");

            //Subclass table
            stmt.execute("CREATE TABLE SUBCLASS (SubclassId number NOT NULL, SubclassName varchar(20) NOT NULL, Description varchar2(100) NOT NULL, ClassId number NOT NULL, BurnId number NOT NULL, ImageLink varchar2(100))");
            stmt.execute("ALTER TABLE SUBCLASS ADD CONSTRAINT SUBC_PK PRIMARY KEY (SubclassId)");
            stmt.execute("ALTER TABLE SUBCLASS ADD FOREIGN KEY (ClassId) REFERENCES CLASS(ClassId)");
            stmt.execute("ALTER TABLE SUBCLASS ADD FOREIGN KEY (BurnId) REFERENCES BURN(BurnId);");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (1, 'Stormcaller', 'electricity', 1, 2, '../Images/stormcaller_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (2, 'Voidwalker', 'bombs', 1, 1, '../Images/voidwalker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (3, 'Sunsinger', 'revive', 1, 3, '../Images/sunsinger_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (4, 'Striker', 'electricity', 2, 2, '../Images/striker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (5, 'Defender', 'shields', 2, 1, '../Images/defender_logo.png')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (6, 'Sunbreaker', 'OP', 2, 3, '../Images/sunbreaker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (7, 'Bladedancer', 'electricity', 3, 2, '../Images/bladedancer_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (8, 'Nightstalker', 'quiver', 3, 1, '../Images/nightstalker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (9, 'Gunslinger', 'golden gun', 3, 3, '../Images/gunslinger_logo.jpg')");


            ResultSet rs = stmt.executeQuery("SELECT * FROM SUBCLASS");
            System.out.println("Class table:");
            while (rs.next()) {
                System.out.println(rs.getString("SubclassName"));
            }
            stmt.close();
            connection.commit();

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
