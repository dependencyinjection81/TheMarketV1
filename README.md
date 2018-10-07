This is supposed to be a Registration/Login/Useraccounting application.

To get this running do the following steps.

Clone repository.
Create a new Maven project.
Import the project nested inside the repository.
If you are using Eclipse you need to install the STS-toolsuite (spring)
Copy the downloaded MySQL Database 5.7 binaries (NOT the installation) into a folder of your choice.
Install the MySQL Workbench
Before you can run the Spring-boot application you have to set up your database by following these Steps

1.)initialize new database:
	a.) create data folder or clear it if exist 
	b.) delete data folder
	c.) mysqld --initialize
	d.) user and password has been created and can be found in the err log file inside datafolder

2.)start database server: mysqld --console

3.)login to database: mysql -u root -p

4.)change default password: alter user 'root'@'localhost' identified by 'pwd' ;

5.)exit database: quit

6.)login again to check if it worked: mysql -u root -p

7.)create a new database: CREATE DATABASE name;

8.)create new user: CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';

9.)give permissions: GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

10:) Refresh: FLUSH PRIVILEGES;

11.) Change authentication method: ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'newrootpassword';

Once u got this to work you will need to edit the application.properties file to fit your credentials for the database.

You have to create a table "Roles" with ROLE_USER ROLE_ADMIN and ROLE_USERNOTVERIFIED and assign the following longs as id'S 10 20 30

When you start the application for the first time it will create automatically the rest of needed tables by itself.
Don't forget to start the database server before you start the application by going into the bin folder of the MySQL installation and type in: mysqld --console

Entrypoint for the application on your machine will be http://localhost:8080/index as soon as u got it to run.

