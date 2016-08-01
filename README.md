# Minecraft & Drools Game
This repository contains the research being done by the Google Summer of Code Program 2016. For more information visit: https://salaboy.com/2016/04/25/google-summer-of-code-2016-drools-minecraft/

We will be updating this repository with a Minecraft MOD which uses the Rule Engine to get data from and influence the Minecraft World.

#Installation / Setup

- Install GIT, Java and Gradle
- git clone https://github.com/youthfulIdealism/drools_mc_integration
- cd drools_mc_integration
- gradlew setupDecompWorkspace --refresh-dependencies
- If you are using eclipse or intelliJ you can run the following goals: http://www.minecraftforge.net/wiki/Installation/Source
  - Eclipse: gradlew eclipse
  - IntelliJ: gradlew genIntellijruns 
- Load the project into your IDE
  - For netbeans look at this link: https://blogs.oracle.com/geertjan/entry/seamless_minecraft_forge_in_netbeans   
  - You can run now the Gradle Tasks -> Run -> Run Client 

#Issues
Please feel free to get in touch or report issues about this project.
  
#Mod
Navigate to the project's root directory, and run gradlew build. Install forge on your copy of minecraft:
http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.9.4.html
Navigate to minecraft's files, and copy minecraft-drools-game-mod/build/libs/minecraft-drools-game-1.0 from your workspace into the mod folder in minecraft's files. Launch Minecraft.
