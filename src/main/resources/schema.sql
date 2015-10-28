CREATE TABLE BURN (
  BurnId number NOT NULL,
  BurnName varchar2(20),
  ImageLink varchar2(100)
);

ALTER TABLE BURN ADD CONSTRAINT BURN_PK PRIMARY KEY (BurnId);

CREATE TABLE 'CLASS' (
  ClassId number NOT NULL,
  ClassName varchar2(20) NOT NULL,
  Description varchar2(100) NOT NULL,
  ImageLink varchar2(100)
);

ALTER TABLE 'CLASS' ADD CONSTRAINT CLASS_PK PRIMARY KEY (ClassId);

CREATE TABLE SUBCLASS (
  SubclassId number NOT NULL,
  SubclassName varchar(20) NOT NULL,
  Description varchar2(100) NOT NULL,
  ClassId number NOT NULL,
  BurnId number NOT NULL,
  ImageLink varchar2(100)
);

ALTER TABLE SUBCLASS ADD PRIMARY KEY (SubclassId);

ALTER TABLE SUBCLASS ADD FOREIGN KEY (ClassId) REFERENCES 'CLASS'(ClassId);

ALTER TABLE SUBCLASS ADD FOREIGN KEY (BurnId) REFERENCES BURN(BurnId);

CREATE TABLE ABILITY (
  AbilityId number NOT NULL,
  AbilityName varchar(20) NOT NULL,
  SubclassId number NOT NULL,
  Description varchar2(100) NOT NULL,
  ColumnNumber number NOT NULL
);

ALTER TABLE ABILITY ADD PRIMARY KEY (AbilityId);

ALTER TABLE ABILITY ADD FOREIGN KEY (SubclassId) REFERENCES SUBCLASS(SubclassId);

CREATE TABLE PLANETZONE (
  ZoneId number NOT NULL,
  ZoneName varchar2(50) NOT NULL,
  PlanetId number NOT NULL
);

CREATE TABLE PLANET (
  PlanetId number NOT NULL,
  PlanetName varchar2(20) NOT NULL,
  ImageLink varchar2(100) NOT NULL,
  MapLink varchar2(100) NOT NULL
);

CREATE TABLE ACTIVITY (
  ActivityId number NOT NULL,
  ActivityName varchar2(50) NOT NULL,
  Boss varchar2(50),
  PlanetId number,
  ImageLink varchar2(100),
  Descriptor varchar2(100)
);

ALTER TABLE PLANETZONE
  ADD CONSTRAINT ZONE_PK PRIMARY KEY (ZoneId);

ALTER TABLE PLANET
  ADD CONSTRAINT PLANET_PK PRIMARY KEY (PlanetId);

ALTER TABLE PLANETZONE
  ADD CONSTRAINT ZONE_PLANET_FK FOREIGN KEY (PlanetId) REFERENCES PLANET(PlanetId);

ALTER TABLE ACTIVITY
  ADD CONSTRAINT ACTIVITY_PK PRIMARY KEY (ActivityId);

ALTER TABLE ACTIVITY
  ADD CONSTRAINT ACTIVITY_PLANET_FK FOREIGN KEY (PlanetId) REFERENCES PLANET(PlanetId);

CREATE TABLE VENDOR (
  VendorId number NOT NULL,
  VendorName varchar2(20) NOT NULL,
  Location varchar2(20) NOT NULL
);

CREATE TABLE DROPSOURCE (
  DropSourceId number NOT NULL,
  DropSourceName varchar2(50) NOT NULL,
  VendorId number,
  ActivityId number
);

ALTER TABLE VENDOR
  ADD CONSTRAINT VENDOR_PK PRIMARY KEY (VendorId);

ALTER TABLE DROPSOURCE
  ADD CONSTRAINT DROPSOURCE_PK PRIMARY KEY (DropSourceId);

ALTER TABLE DROPSOURCE
  ADD CONSTRAINT DROPSOURCE_VENDOR_FK FOREIGN KEY (VendorId) REFERENCES VENDOR(VendorId);

ALTER TABLE DROPSOURCE
  ADD CONSTRAINT DROPSOURCE_ACTIVITY_FK FOREIGN KEY (ActivityId) REFERENCES ACTIVITY(ActivityId);

CREATE TABLE TIER (
  TierId number NOT NULL,
  TierName varchar2(20) NOT NULL
);

CREATE TABLE ARMOR (
  ArmorId number NOT NULL,
  ArmorName varchar2(50) NOT NULL,
  ArmorType varchar2(20) NOT NULL,
  TierId number NOT NULL,
  ExoticPerkId number,
  ClassId number NOT NULL,
  ImageLink varchar2(100)
);

CREATE TABLE ARMORDROPSOURCE (
  ArmorId number NOT NULL,
  DropSourceId number NOT NULL,
  ArmorDropSourceId number NOT NULL
);

CREATE TABLE WEAPON (
  Weaponid number NOT NULL,
  FireRate number,
  ChargeRate number,
  Range number,
  Impact number,
  Reload number,
  Stability number,
  WeaponName varchar2(20) NOT NULL,
  TierId number NOT NULL,
  BurnId number,
  ExoticPerkId number,
  ImageLink varchar2(100),
  ClassId number
);

CREATE TABLE WEAPONDROPSOURCE (
  WeaponId number NOT NULL,
  DropSourceId number NOT NULL,
  WeaponDropSourceId number NOT NULL
);

CREATE TABLE EXOTICPERK (
  ExoticPerkId number NOT NULL,
  PerkName varchar2(20) NOT NULL,
  Description varchar2(100) NOT NULL
);

ALTER TABLE EXOTICPERK
  ADD CONSTRAINT EXPERK_PK PRIMARY KEY (ExoticPerkId);

ALTER TABLE TIER
  ADD CONSTRAINT TIER_PK PRIMARY KEY (TierId);

ALTER TABLE ARMOR
  ADD CONSTRAINT ARMOR_PK PRIMARY KEY (ArmorId);

ALTER TABLE ARMOR
  ADD CONSTRAINT ARMOR_TIER_FK FOREIGN KEY (TierId) REFERENCES TIER(TierId);

ALTER TABLE ARMOR
  ADD CONSTRAINT ARMOR_EXPERK_FK FOREIGN KEY (ExoticPerkId) REFERENCES EXOTICPERK(ExoticPerkId);

ALTER TABLE ARMOR
  ADD CONSTRAINT ARMOR_CLASS_FK FOREIGN KEY (ClassId) REFERENCES CLASS(ClassId);

ALTER TABLE ARMORDROPSOURCE
  ADD CONSTRAINT ARMORSOURCE_PK PRIMARY KEY (ArmorId, DropSourceId);

ALTER TABLE WEAPON
  ADD CONSTRAINT WEAPON_PK PRIMARY KEY (WeaponId);

ALTER TABLE WEAPON
  ADD CONSTRAINT WEAPON_TIER_FK FOREIGN KEY (TierId) REFERENCES TIER(TierId);

ALTER TABLE WEAPON
  ADD CONSTRAINT WEAPON_BURN_Fk FOREIGN KEY (BurnId) REFERENCES BURN(BurnId);

ALTER TABLE WEAPON
  ADD CONSTRAINT WEAPON_EXPERK_FK FOREIGN KEY (ExoticPerkId) REFERENCES EXOTICPERK(ExoticPerkId);

ALTER TABLE WEAPON
  ADD CONSTRAINT WEAPON_CLASS_FK FOREIGN KEY (ClassId) REFERENCES CLASS(ClassId);

ALTER TABLE WEAPONDROPSOURCE
  ADD CONSTRAINT WEAPONSOURCE_PK PRIMARY KEY (Weaponid, DropSourceId);
