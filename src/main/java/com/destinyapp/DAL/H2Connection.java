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
            ////Stormcaller
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

            ////Sunsinger
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (28, 'Solar Grenade', 3, 'A grenade that creates a flare of Solar Light which continually damages enemies trapped inside.', 1, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (29, 'Firebolt Grenade', 3, 'A grenade that unleashes bolts of Solar Light at nearby enemies.', 1, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (30, 'Fusion Grenade', 3, 'A grenade that causes bonus damage when attached to its target.', 1, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (31, 'Glide', 3, 'Double jump in mid-air to activate glide.', 2, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (32, 'Focused Control', 3, 'Upgrades Glide for better directional control while in mid-air.', 2, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (33, 'Focused Burst', 3, 'Upgrades Glide to provide an initial burst of speed.', 2, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (34, 'Balanced Glide', 3, 'Upgrades Glide to provide bonuses to both speed and control.', 2, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (35, 'Radiance', 3, 'Fill yourself with Solar light, dramatically increasing the effectiveness of all your abilities.', 3, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (36, 'Song of Flame', 3, 'While active, Radiance reduces all cooldowns for nearby allies.', 3, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (37, 'Radiant Skin', 3, 'While active, Radiance reduces incoming damage.', 3, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (38, 'Fireborn', 3, 'Radiance can now be activated from beyond the grave. Doing so returns you to life.', 3, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (39, 'Scorch', 3, 'A powerful melee attack that ignites enemies, causing damage over time.', 4, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (40, 'Flame Shield', 3, 'Damaging enemies with Scorch reduces incoming damage for a short time.', 4, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (41, 'Solar Wind', 3, 'Hitting an enemy with Scorch knocks them back.', 4, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (42, 'Brimstone', 3, 'Killing enemies with Scorch causes them to explode.', 4, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (43, 'Arcane Wisdom', 3, 'Training focused on battle recovery and speed.', 5, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (44, 'Arcane Spirit', 3, 'Training focused on battle recovery and toughness.', 5, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (45, 'Arcbolt Force', 3, 'Training focused on toughness and speed.', 5, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (46, 'Radiant Will', 3, 'Increases the duration of Radiance.', 6, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (47, 'Viking Funeral', 3, 'Enemies you ignite burn longer and take more damage.', 6, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (48, 'Sunburst', 3, 'Killing an enemy with Scorch has a chance to generate Orbs of Light.', 6, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (49, 'Ancestral Order', 3, 'Training focused on all attributes.', 7, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (50, 'Chaos Order', 3, 'Training focused on speed.', 7, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (51, 'Divine Order', 3, 'Training focused on toughness at all costs.', 7, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (52, 'Touch of Flame', 3, 'All grenades ignite enemies, causing damage over time.', 8, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (53, 'Angel of Light', 3, 'Aiming your weapon while in the air will hold you in place for a short time.', 8, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (54, 'Gift of the Sun', 3, 'Gain an additional grenade.', 8, 3)");

            ////Voidwalker
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (55, 'Vortex Grenade', 2, 'A grenade that creates a vortex which continually damages enemies inside the sphere.', 1, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (56, 'Scatter Grenade', 2, 'A grenade that splits into many submunitions and covers a large area with explosions.', 1, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (57, 'Axion Grenade', 2, 'A bolt of Void light which forks into smaller bolts on impact which seek toward enemies.', 1, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (58, 'Glide', 2, 'Double jump in mid-air to activate glide.', 2, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (59, 'Focused Control', 2, 'Upgrades Glide for better directional control while in mid-air.', 2, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (60, 'Focused Burst', 2, 'Upgrades Glide to provide an initial burst of speed.', 2, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (61, 'Blink', 2, 'A short distance teleport that replaces Glide.', 2, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (62, 'Novabomb', 2, 'Hurl an explosive bolt of Void light at the enemy, disintegrating those caught within its blast.', 3, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (63, 'Vortex', 2, 'Novabomb creates a vortex which continually damages enemies trapped inside. Lasts 3.5 seconds.', 3, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (64, 'Shatter', 2, 'Novabomb splits into three projectiles.', 3, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (65, 'Lance', 2, 'Novabomb travels farther and faster.', 3, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (66, 'Energy Drain', 2, 'A powerful melee attack which drains energy from enemies and uses it to reduce the cooldown of your grenade.', 4, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (67, 'Surge', 2, 'Damaging an enemy with Energy Drain increases your weapon and movement speed.', 4, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (68, 'Life Steal', 2, 'Killing an enemy with Energy Drain immediately restores a significant portion of your health.', 4, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (69, 'Soul Rip', 2, 'Killing an enemy with Energy Drain reduces the cooldown of Nova Bomb.', 4, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (70, 'Arcane Wisdom', 2, 'Training focused on battle recovery and speed.', 5, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (71, 'Arcane Spirit', 2, 'Training focused on battle recovery and toughness.', 5, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (72, 'Arcbolt Force', 2, 'Training focused on toughness and speed.', 5, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (73, 'Annihilate', 2, 'Increases the size of the explosion created by Nova Bomb and Vortex grenade.', 6, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (74, 'Angry Magic', 2, 'Novabomb tracks enemies.', 6, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (75, 'The Hunger', 2, 'Increases the duration of the Energy Drain effect.', 6, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (76, 'Ancestral Order', 2, 'Training focused on all attributes.', 7, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (77, 'Chaos Order', 2, 'Training focused on speed.', 7, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (78, 'Divine Order', 2, 'Training focused on toughness at all costs.', 7, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (79, 'Vortex Mastery', 2, 'Increases the range of Axion Bolt seekers and the duration of the Vortex effect of Nova Bomb and Vortex Grenade.', 8, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (80, 'Bloom', 2, 'Enemies killed with any of your abilities explode.', 8, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (81, 'Embrace the Void', 2, 'Damaging enemies with Nova Bomb or any grenade triggers the Energy Drain effect.', 8, 3)");

            //Titan
            ///Striker
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (82, 'Flashbang Grenade', 4, 'An explosive grenade that disorients the enemies it damages.', 1, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (83, 'Pulse Grenade', 4, 'A grenade that periodically damages enemies inside its explosive radius.', 1, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (84, 'Lightning Grenade', 4, 'A grenade that sticks to any surface, periodically emitting bolts of lightning.', 1, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (85, 'Lift', 4, 'Jump and press again while in the air to activate Lift.', 2, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (86, 'Increased Height', 4, 'Upgrades Lift to travel to greater heights.', 2, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (87, 'Increased Control', 4, 'Upgrades Lift for better directional control while in the air.', 2, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (88, 'Catapult', 4, 'Upgrades Lift to provide a strong initial burst of momentum.', 2, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (89, 'Fist of Havoc', 4, 'Smash the ground and dissolve nearby enemies in a maelstrom of Arc Light.', 3, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (90, 'Aftermath', 4, 'Fist of Havoc leaves a damage-dealing field in its wake.', 3, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (91, 'Death from Above', 4, 'After jumping, Fist of Havoc can be aimed at enemies below.', 3, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (92, 'Shockwave', 4, 'Fist of Havoc unleashes a wave of devastating energy which travels along the ground.', 3, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (93, 'Storm Fist', 4, 'A punishing melee attack that deals bonus damage.', 4, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (94, 'Overload', 4, 'Hits with Storm Fist have a chance to immediately reset its cooldown.', 4, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (95, 'Discharge', 4, 'Hits with Storm Fist deal area of effect damage around the target.', 4, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (96, 'Amplify', 4, 'Kills with Storm Fist significantly reduce the cooldown of Fist of Havoc.', 4, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (97, 'Titan Codex I', 4, 'Training focused on battle recovery and toughness.', 5, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (98, 'Titan Codex II', 4, 'Training focused on speed and toughness.', 5, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (99, 'Titan Codex III', 4, 'Training focused on battle recovery and speed.', 5, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (100, 'Headstrong', 4, 'Sprinting increases the leap distance of Fist of Havoc.', 6, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (101, 'Aftershocks', 4, 'Increases the duration of the Pulse Grenade, Lightning Grenade, and Aftermath.', 6, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (102, 'Transfusion', 4, 'Kills with Storm Fist or Shoulder Charge immediately trigger health regeneration.', 6, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (103, 'Titan Codex IV', 4, 'Training focused on all attributes.', 7, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (104, 'Titan Codex V', 4, 'Training focused on maximum battle recovery.', 7, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (105, 'Titan Codex VI', 4, 'Training focused on raw speed.', 7, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (106, 'Unstoppable', 4, 'You are harder to kill while using Fist of Havoc.', 8, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (107, 'Shoulder Charge', 4, 'After sprinting for a short time, unleash a devastating melee attack.', 8, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (108, 'Juggernaut', 4, 'After sprinting for a short time, gain a protective shield.', 8, 3)");

            ////Defender
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (109, 'Magnetic Grenade', 5, 'A grenade that attaches to enemies and explodes twice.', 1, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (110, 'Spike Grenade', 5, 'A grenade that attaches to any surface and emits a torrent of damaging void light.', 1, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (111, 'Suppressor Grenade', 5, 'An explosive grenade that prevents enemies from using abilities for a short time.', 1, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (112, 'Lift', 5, 'Jump and press again while in the air to activate Lift.', 2, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (113, 'Increased Height', 5, 'Upgrades Lift to travel to greater heights.', 2, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (114, 'Increased Control', 5, 'Upgrades Lift for better directional control while in the air.', 2, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (115, 'Catapult', 5, 'Upgrades Lift to provide a strong initial burst of momentum.', 2, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (116, 'Ward of Dawn', 5, 'Shape Void Light into an indestructible shield to protect you and your allies from harm.', 3, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (117, 'Armor of Light', 5, 'While inside Ward of Dawn you and your allies gain significant damage resistance.', 3, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (118, 'Blessing of Light', 5, 'Passing through Ward of Dawn grants you and your allies a temporary shield.', 3, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (119, 'Weapons of Light', 5, 'Passing through Ward of Dawn grants you and your allies a temporary increase to weapon damage.', 3, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (120, 'Disintegrate', 5, 'Killing an enemy with this powerful melee attack creates a Force Barrier around you which absorbs incoming damage. Force Barrier lasts 15 seconds.', 4, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (121, 'War Machine', 5, 'While Force Barrier is active all your weapons reload and ready blindingly fast.', 4, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (122, 'Gift of Light', 5, 'When the shield created by Disintegrate is active all of your melee kills generate Orbs of Light.', 4, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (123, 'Unbreakable', 5, 'Force Barrier continually recharges.', 4, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (124, 'Titan Codex I', 5, 'Training focused on battle recovery and toughness.', 5, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (125, 'Titan Codex II', 5, 'Training focused on speed and toughness.', 5, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (126, 'Titan Codex III', 5, 'Training focused on battle recovery and speed.', 5, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (127, 'Bastion', 5, 'Increases the duration of Ward of Dawn to 45 seconds.', 6, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (128, 'Relentless', 5, 'Increases the duration and strength of the Force Barrier created by Disintegrate.', 6, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (129, 'Gift of the Void', 5, 'As Ward of Dawn takes damage from enemy fire it creates additional Orbs of Light.', 6, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (130, 'Titan Codex IV', 5, 'Training focused on all attributes.', 7, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (131, 'Titan Codex V', 5, 'Training focused on maximum battle recovery.', 7, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (132, 'Titan Codex VI', 5, 'Training focused on raw speed.', 7, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (133, 'Untouchable', 5, 'Reduces the cooldown time of Ward of Dawn.', 8, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (134, 'Iron Harvest', 5, 'Heavy Weapon kills have a chance to create Orbs of Light for your allies.', 8, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (135, 'Illuminated', 5, 'Increases the benefits granted by Blessing of Light and Weapons of Light.', 8, 3)");

            //Hammerbro
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (136, 'Fusion Grenade', 6, 'A grenade that causes bonus damage when attached to its target.', 1, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (137, 'Thermite Grenade', 6, 'Grenade explosion sends forward a burning line of fire.', 1, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (138, 'Incendiary Grenade', 6, 'An explosive grenade that sets enemies on fire, causing additional damage to them.', 1, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (139, 'Lift', 6, 'Jump and press again while in the air to activate Lift.', 2, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (140, 'Increased Height', 6, 'Upgrades Lift to travel to greater heights.', 2, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (141, 'Increased Control', 6, 'Upgrades Lift for better directional control while in the air.', 2, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (142, 'Catapult', 6, 'Upgrades Lift to provide a strong initial burst of momentum.', 2, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (143, 'Hammer of Sol', 6, 'Summon a flaming hammer and wreak destruction down upon your enemies.', 3, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (144, 'Forgemaster', 6, 'Throw more hammers, and hammers cause bigger explosions.', 3, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (145, 'Suncharge', 6, 'Press (R1/RB) during Hammer of Sol to hurl yourself forward. Enemies in your path explode, chaining fiery damage to other enemies.', 3, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (146, 'Scorched Earth', 6, 'Ignite the world, creating Sunspots everywhere your Hammer of Sol impacts.', 3, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (147, 'Sunstrike', 6, 'Ignite your enemies with a heavy solar strike.', 4, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (148, 'Melting Point', 6, 'Burn away your targets defense. Targets take more damage from both you and your allies.', 4, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (149, 'Thermal Vent', 6, 'Sunstrike releases a Solar explosion on hit. Kills create a Sunspot that damages enemies inside.', 4, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (150, 'Stoke the Forge', 6, 'Natively reduces the cooldown of your Sunstrike melee. Getting a killing blow with Sunstrike instantly recharges it.', 4, 4)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (151, 'Titan Codex I', 6, 'Training focused on battle recovery and toughness.', 5, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (152, 'Titan Codex II', 6, 'Training focused on speed and toughness.', 5, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (153, 'Titan Codex III', 6, 'Training focused on battle recovery and speed.', 5, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (154, 'Flameseeker', 6, 'Your Hammer of Sol will alter its flight path to seek out your enemies.', 6, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (155, 'Explosive Pyre', 6, 'Enemies felled by your hammer explode, chaining fiery Solar damage to others.', 6, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (156, 'Fleetfire', 6, 'Enemies brought down by your fire grant you bonus agility and reload speed for a short time. Stacks up to 3 times.', 6, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (157, 'Titan Codex IV', 6, 'Training focused on all attributes.', 7, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (158, 'Titan Codex V', 6, 'Training focused on maximum battle recovery.', 7, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (159, 'Titan Codex VI', 6, 'Training focused on raw speed.', 7, 3)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (160, 'Simmering Flames', 6, 'When Super energy is full, grenade and melee abilities recharge twice as fast.', 8, 1)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (161, 'Cauterize', 6, 'Enemies brought down by your fire regenerate your health.', 8, 2)");
            stmt.execute("INSERT INTO ABILITY(AbilityId, AbilityName, SubclassId, Description, ColumnNumber, RowNumber) VALUES (162, 'Fire Keeper', 6, 'When standing in a Sunspot you gain an Overshield and Hammer of Sol lasts longer.', 8, 3)");



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
