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
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (1, 'Stormcaller', 'Harmony within, hurricane without.', 1, 2, '../Images/stormcaller_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (2, 'Voidwalker', 'Those who have stared into the Void are not bound by the laws of space and time.', 1, 1, '../Images/voidwalker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (3, 'Sunsinger', 'There are flames that even the Darkness cannot extinguish.', 1, 3, '../Images/sunsinger_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (4, 'Striker', 'At close quarters, a fist is better than any gun.', 2, 2, '../Images/striker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (5, 'Defender', 'The wall against which the Darkness breaks.', 2, 1, '../Images/defender_logo.png')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (6, 'Sunbreaker', 'Forge the fury of undying suns.', 2, 3, '../Images/sunbreaker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (7, 'Bladedancer', 'Beautiful lethality, relentless style.', 3, 2, '../Images/bladedancer_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (8, 'Nightstalker', 'Draw from the Void. Light the way.', 3, 1, '../Images/nightstalker_logo.jpg')");
            stmt.execute("INSERT INTO SUBCLASS(SubclassId, SubclassName, Description, ClassId, BurnId, ImageLink) VALUES (9, 'Gunslinger', 'A lone wolf who lives for the perfect shot.', 3, 3, '../Images/gunslinger_logo.jpg')");

            //Ability table
            stmt.execute("CREATE TABLE ABILITY (AbilityId number NOT NULL, AbilityName varchar(50) NOT NULL, SubclassId number NOT NULL, Description varchar2(500) NOT NULL, ColumnNumber number NOT NULL, RowNumber number NOT NULL)");
            stmt.execute("ALTER TABLE ABILITY ADD PRIMARY KEY (AbilityId)");
            stmt.execute("ALTER TABLE ABILITY ADD FOREIGN KEY (SubclassId) REFERENCES SUBCLASS(SubclassId)");
            ///Warlock
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (1, 'Pulse Grenade', 1, 'A grenade that periodically damages enemies inside its explosive radius.', 1, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (2, 'Storm Grenade', 1, 'This grenade calls down a localized lightning storm.', 1, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (3, 'Arcbolt Grenade', 1, 'A grenade that chains bolts of lightning to nearby enemies.', 1, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (4, 'Glide', 1, 'Double jump in mid-air to activate glide.', 2, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (5, 'Focused Control', 1, 'Upgrades Glide for better directional control while in mid-air.', 2, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (6, 'Focused Burst', 1, 'Upgrades Glide to provide an initial burst of speed.', 2, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (7, 'Balanced Glide', 1, 'Upgrades Glide to provide bonuses to both speed and control.', 2, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (8, 'Stormtrance', 1, 'Hold R2/RT to fire Arc lightning from your hands.', 3, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (9, 'Landfall', 1, 'On casting Stormtrance, fire a bolt of lightning into the ground, creating a devastating shockwave under you.', 3, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (10, 'Superconductor', 1, 'Doubles your Stormtrance lightnings chaining capabilities.', 3, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (11, 'Ionic Blink', 1, 'Press L3 to teleport during Stormtrance.', 3, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (12, 'Thunderstrike', 1, 'Deliver an electrocuting melee strike to close-range targets.', 4, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (13, 'Chain Lightning', 1, 'Your Thunderstrike chains from the struck target to another nearby enemy.', 4, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (14, 'Amplitude', 1, 'Your Thunderstrike has a greater range.', 4, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (15, 'Rising Storm', 1, 'Hits with Thunderstrike charge your Super Ability and Melee energy.', 4, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (16, 'Arcane Wisdom', 1, 'Training focused on battle recovery and speed.', 5, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (17, 'Arcane Spirit', 1, 'Training focused on battle recovery and toughness.', 5, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (18, 'Arcbolt Force', 1, 'Training focused on toughness and speed.', 5, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (19, 'Pulse Wave', 1, 'When critically wounded, trigger a Pulsewave that boosts speed for you and your allies.', 6, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (20, 'Feedback', 1, 'Incoming melee attacks fully recharge and intensify your Thunderstrike.', 6, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (21, 'Transcendence', 1, 'When cast with full grenade and melee energy, Stormtrance restores your health to full and drains slower.', 6, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (22, 'Ancestral Order', 1, 'Training focused on all attributes.', 7, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (23, 'Chaos Order', 1, 'Training focused on speed.', 7, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (24, 'Divine Order', 1, 'Training focused on toughness at all costs.', 7, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (25, 'Electrostatic Mind', 1, 'Stormtrance charges faster when allies are near. When Stormtrance is active, nearby enemies take damage.', 8, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (26, 'Arc Web', 1, 'Enemies damaged by your grenades chain deadly lightning to other nearby enemies.', 8, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (27, 'Perpetual Charge', 1, 'Getting a grenade kill recharges your melee. Getting a melee kill recharges your grenade.', 8, 3)");








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
