 CREATE TABLE product
  (
     id    INTEGER NOT NULL auto_increment,
     name  VARCHAR(255),
     price FLOAT NOT NULL,
     PRIMARY KEY (id)
  )
engine=innodb  
