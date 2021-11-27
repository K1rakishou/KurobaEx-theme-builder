# KurobaEx-theme-builder

[<img src="screenshots/1.png" width=1024>](screenshots/1.png)

# How to run

You need IntelliJ IDEA to build/run this.

1. Clone the project. 
2. Import it (Project from VCS/Version Control).
3. Sync (should start automatically after importing the project). Wait until it's done.

![image](https://user-images.githubusercontent.com/10332458/143673664-99f78381-c0d5-4dcb-b8a6-161331892cdc.png)

4. Create a gradle task to run the program. Click "Add configuration" in the top-right corner to create a new task.

![image](https://user-images.githubusercontent.com/10332458/143674412-71509733-f177-4d81-8b09-76f69354d227.png)

5. Click the plus + icon and then click Application. 

![image](https://user-images.githubusercontent.com/10332458/143674440-45efad51-d7c2-42ba-b667-b8bac12e1bbf.png)

6. In the Main class options enter MainKt and make sure it points to the project and not one of the unrelated Main classes. 

![image](https://user-images.githubusercontent.com/10332458/143674295-0291ff80-4453-4a38-b6db-0b4710a70852.png)

7. In the module options select compose-test.main.

![image](https://user-images.githubusercontent.com/10332458/143674318-851e7fe4-1978-4064-860f-6aef39d81b53.png)

8. Then select what Java compiler to use (15 is recommended).

![image](https://user-images.githubusercontent.com/10332458/143674336-4e606b8a-e78b-449a-a480-127dc4869b77.png)

![image](https://user-images.githubusercontent.com/10332458/143674267-b1e1e458-fde7-439c-8295-45da16c8a0a2.png)

9. Click OK and then click Run. This should run the program.

![image](https://user-images.githubusercontent.com/10332458/143674377-d96c08f7-b3bb-418b-a6b5-b88fc1dcd4d6.png)
