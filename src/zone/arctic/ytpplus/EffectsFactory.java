/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zone.arctic.ytpplus;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

    
public class EffectsFactory {
    
    public Utilities toolBox;
    
    public EffectsFactory(Utilities utilities) {
        this.toolBox = utilities;
    }
    
    public String pickSound() {
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercase = name.toLowerCase();
                if (lowercase.endsWith(".mp3")) 
                    return true;
                else 
                    return false;
            }
        };
        File[] files = new File(toolBox.SOUNDS).listFiles(fileFilter);
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        return file.getName();
    }
    public String pickSource() {
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercase = name.toLowerCase();
                if (lowercase.endsWith(".mp4")) 
                    return true;
                else 
                    return false;
            }
        };
        File[] files = new File(toolBox.SOURCES).listFiles(fileFilter);
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        return file.getName();
    }
    
    public String pickMusic() {
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercase = name.toLowerCase();
                if (lowercase.endsWith(".mp3")) 
                    return true;
                else 
                    return false;
            }
        };
        File[] files = new File(toolBox.MUSIC).listFiles(fileFilter);
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        return file.getName();
    }
    
    /* EFFECTS */
    public void effect_RandomSound(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String randomSound = pickSound();
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -i "+ toolBox.SOUNDS + randomSound + " -c:v copy -ar 44100 -ac 2 -filter_complex \"[1:a]apad[A];[0:a][A]amix,volume=2[out]\" -map 0:v -map [out] -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }   
    public void effect_RandomSoundMute(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            String randomSound = pickSound();
            String soundLength = toolBox.getLength(toolBox.SOUNDS+randomSound);
            System.out.println("Doing a mute now. " + randomSound + " length: " + soundLength + ".");
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -i " + toolBox.SOUNDS + randomSound + " -map 0:v -map 1:a -c:v copy -ar 44100 -ac 2 -to " + soundLength + " -shortest -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }   
    public void effect_Reverse(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String randomSound = pickSound();
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -vf reverse -af areverse -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }  
    
    
    public void effect_SpeedUp(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -vf setpts=.5*PTS -af atempo=2 -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }   
    
    public void effect_SlowDown(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -vf setpts=2*PTS -af atempo=.5 -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {
            System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);
        }
    }   
    
    public void effect_Chorus(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -c:v copy -af chorus=0.5:0.9:50|60|40:0.4|0.32|0.3:0.25|0.4|0.3:2|2.3|1.3 -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }   

    public void effect_Vibrato(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -c:v copy -af vibrato=f=7:d=.5 -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    } 
    
    public void effect_LowPitch(String video) {
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -vf setpts=2*PTS -af asetrate=22050,aresample=44100 -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }

    public void effect_HighPitch(String video) {
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            File temp = new File(toolBox.TEMP + "temp.mp4");
            if (in.exists())
                in.renameTo(temp);
            String command = toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -vf setpts=.5*PTS -af asetrate=88200,aresample=44100 -y " + video;
            CommandLine cmdLine = CommandLine.parse(command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            temp.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }
    
    public void effect_Dance(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            
            File temp = new File(toolBox.TEMP + "temp.mp4");
            File temp2 = new File(toolBox.TEMP + "temp2.mp4");
            File temp3 = new File(toolBox.TEMP + "temp3.mp4");
            
            // final result is backwards & forwards concatenated with music
            
            if (in.exists())
                in.renameTo(temp);
            if (temp2.exists())
                temp2.delete();
            if (temp3.exists())
                temp3.delete();
            
            String randomSound = pickMusic();
            ArrayList<String> commands = new ArrayList<String>();
            int randomTime = randomDouble(0.3,2.0);
            commands.add(toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -an -vf setpts=.5*PTS -to " + randomTime + " -y " + toolBox.TEMP + "temp2.mp4");
            
            commands.add(toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp2.mp4 -vf reverse -y " + toolBox.TEMP + "temp3.mp4");
            
            commands.add(toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp3.mp4 -i " + toolBox.TEMP + "temp2.mp4 -i " + toolBox.MUSIC + randomSound + " -filter_complex \"[0:v][1:v][0:v][1:v][0:v][1:v][0:v][1:v]concat=n=8:v=1[out]\" -map [out] -map 2:a -shortest -y " + toolBox.TEMP + "temp4.mp4");
            
            for (String command : commands) {
                CommandLine cmdLine = CommandLine.parse(command);
                DefaultExecutor executor = new DefaultExecutor();
                int exitValue = executor.execute(cmdLine);
            }
            
            temp.delete();
            temp2.delete();
            temp3.delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }  
    
    public void effect_Squidward(String video){
        System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + " initiated");
        try {
            File in = new File(video);
            
            File temp = new File(toolBox.TEMP + "temp.mp4"); //og file
            
            if (in.exists())
                in.renameTo(temp);
            
            ArrayList<String> commands = new ArrayList<String>();

            commands.add(toolBox.FFMPEG + " -i " + toolBox.TEMP + "temp.mp4 -vf \"select=gte(n\\,1)\" -vframes 1 -y " + toolBox.TEMP + "squidward0.png");
            
            for (int i=1; i<6; i++) { 
                String effect = "";
                int random = randomInt(0,7);
                switch (random) {
                    case 0: 
                        effect = "flop";
                        break;
                    case 1:
                        effect = "flip";
                        break;
                    case 2:
                        effect = "rotate 180";
                        break;
                    case 3:
                        effect = "implode -" + randomInt(1,3);
                        break;
                    case 4:
                        effect = "implode " + randomInt(1,3);
                        break;
                    case 5:
                        effect = "swirl " + randomInt(1,180);
                        break;
                    case 6:
                        effect = "swirl -" + randomInt(1,180);
                        break;
                    case 7:
                        effect = "channel RGB -negate";
                        break;
                }
                commands.add(toolBox.MAGICK + " convert " + toolBox.TEMP + "squidward0.png -" + effect + " " + toolBox.TEMP + "squidward" + i + ".png"
                );
            }
            commands.add(toolBox.MAGICK + " convert -size 640x480 canvas:black " + toolBox.TEMP + "black.png");
            
            if (new File(toolBox.TEMP + "concatsquidward.txt").exists())
                new File(toolBox.TEMP + "concatsquidward.txt").delete();
            PrintWriter writer = new PrintWriter(toolBox.TEMP + "concatsquidward.txt", "UTF-8");
            writer.write
                        ("file 'squidward0.png'\n" +
                        "duration 0.467\n" +
                        "file 'squidward1.png'\n" +
                        "duration 0.434\n" +
                        "file 'squidward2.png'\n" +
                        "duration 0.4\n" +
                        "file 'black.png'\n" +
                        "duration 0.834\n" +
                        "file 'squidward3.png'\n" +
                        "duration 0.467\n" +
                        "file 'squidward4.png'\n" +
                        "duration 0.4\n" +
                        "file 'squidward5.png'\n" +
                        "duration 0.467");
            writer.close();
            
            commands.add(toolBox.FFMPEG + " -f concat -i " + toolBox.TEMP + "concatsquidward.txt -i " + toolBox.RESOURCES + "squidward/music.wav -pix_fmt yuv420p -shortest -y " + video);
            for (String command : commands) {
                System.out.println("Executing: " + command);
                CommandLine cmdLine = CommandLine.parse(command);
                DefaultExecutor executor = new DefaultExecutor();
                int exitValue = executor.execute(cmdLine);
            }
            
            temp.delete();
            for (int i=0; i<6; i++) {
                new File(toolBox.TEMP + "squidward"+i+".png").delete();
            }
            new File(toolBox.TEMP + "black.png").delete();
            new File(toolBox.TEMP + "concatsquidward.txt").delete();
        } catch (Exception ex) {System.out.println(new Object() {}.getClass().getEnclosingMethod().getName() + "\n" +ex);}
    }  
    
    public static int randomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
    public static double randomDouble(double min, double max)
    {
        double finalVal = -1;
        while (finalVal < 0)
        {
            double x = (rnd.NextDouble() * ((max - min) + 0)) + min;
            finalVal = Math.Round(x * 100.0) / 100.0;
        }
        return finalVal;
    }
}
