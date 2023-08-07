CREATE TABLE IF NOT EXISTS book (
  id INT NOT NULL AUTO_INCREMENT, 
  title VARCHAR(255), 
  description VARCHAR(255), 
  published BOOLEAN, 
  PRIMARY KEY (id)
);
