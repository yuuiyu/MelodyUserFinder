package nmsl;

import by.radioegor146.nativeobfuscator.Native;
import by.radioegor146.nativeobfuscator.NotNative;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Session;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


@Mod(modid = "melody_user_finder")
@Native
public class HelloMelody extends CommandBase {
    public static final String NMSL="Mod by Yuuiyu NMSL Melody";
    private HashMap<String,String> users=new HashMap<>();

    public HelloMelody() throws AWTException {
    }

    @Mod.EventHandler
    public void inti(FMLPreInitializationEvent e) {
        ClientCommandHandler.instance.registerCommand(this);
        MinecraftForge.EVENT_BUS.register(this);
        loadUsers();
        
        Session session=new Session("Yuuiyu","06ae8087-14e0-44a3-99fd-da10294794d7"
                ,"eyJraWQiOiJhYzg0YSIsImFsZyI6IkhTMjU2In0.eyJ4dWlkIjoiMjUzNTQxNDc5MzA0MzUxNSIsImFnZyI6IkFkdWx0Iiwic3ViIjoiNzQyYjMwOTMtNzE2Ni00MDQyLTljYjAtMzZhNWFmYmEzNTcyIiwibmJmIjoxNjc0OTg2ODk2LCJhdXRoIjoiWEJPWCIsInJvbGVzIjpbXSwiaXNzIjoiYXV0aGVudGljYXRpb24iLCJleHAiOjE2NzUwNzMyOTYsImlhdCI6MTY3NDk4Njg5NiwicGxhdGZvcm0iOiJVTktOT1dOIiwieXVpZCI6ImM4OGUzMjUwYTRhOGM3N2ViYzJhMjYxODVkMGZjMDcyIn0.uyCTZi0kb43jIXWrPcxKZ3s2MCYnxdVBR7BQJFETK18"
                ,"mojang");
        try {
            Minecraft.getMinecraft().getClass().getDeclaredField("session").setAccessible(true);
            Minecraft.getMinecraft().getClass().getDeclaredField("session").set(Minecraft.getMinecraft(),session);
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        }

    }

    private void loadUsers(){
        new Thread(()->{
            users=new HashMap<>();
            String s= null;
            try {

                s = IOUtils.toString(new URI("https://raw.githubusercontent.com/NMSLAndy/MelodySky-UUID/main/UUIDs.json"));
            } catch (Exception e) {
                e.printStackTrace();
                if(Minecraft.getMinecraft().theWorld!=null){
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b] "+"§b加载失败请重试！"));
                }
            }
            String []t=s.split("\n");
            for (String d: t) {
                String[] a=d.split(":") ;
                if(a.length==2)
                    users.put(a[1].trim(),a[0]);
            }
            if(Minecraft.getMinecraft().theWorld!=null){
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b] "+"§b加载完成,共"+users.size()+"个对象！"));}
        }).start();
    }
    List<String> userss=null;
    public static int abc=0;
    public static World w;
    int tick;
    public HashSet<String> te;
    boolean flag=false;

    Robot robot=new Robot();
    @SubscribeEvent
    public void ontick(TickEvent.ClientTickEvent e){
        tick++;
        if(tick==160){
            tick=0;
        }
                if(MinecraftServer.getServer()!=null&&Minecraft.getMinecraft().theWorld!=null&&toggle&&userss!=null&&abc==1&&tick==70){
                    te=new HashSet<>();
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b] "+"§b<§e-----start-----§b>"));
                    int size=0;

                        for (String ee: userss) {
                            if(debug){
                                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b][Debug] 正在对："+ee+" 检查"));
                            }
                            if(users.get(ee)!=null){

                                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§bUSERNAME: §e "+ee+" §bUUID: §e"+users.get(ee)));
                                size++;
                        }
                    }
                    robot.keyRelease(KeyEvent.VK_TAB);
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b] "+"§b<§e------end------§b> 共找到："+size+"/"+this.users.size()));
                    abc=0;
                }

        if(MinecraftServer.getServer()!=null&&Minecraft.getMinecraft().theWorld!=null&&toggle&&Minecraft.getMinecraft().theWorld!=this.w){
            GuiChat gc=new GuiChat();
            Minecraft.getMinecraft().displayGuiScreen(gc);
            Field f= null;
            Class c=GuiChat.class;
            try {
                f = c.getDeclaredField("field_146415_a");
//                f = c.getDeclaredField("inputField");
                f.setAccessible(true);
                ( (GuiTextField)f.get(gc)).setText(" ");
            } catch (Exception eee) {
                eee.printStackTrace();
            }
//            try {
//                f = c.getDeclaredField("field_146417_i");
//                f = c.getDeclaredField("inputField");
//                f.setAccessible(true);
//                f.setBoolean(gc,true);
//            } catch (Exception eee) {
//                eee.printStackTrace();
//            }


            robot.keyPress(KeyEvent.VK_TAB);
            tick=0;
            flag=true;
            userss=new ArrayList<>();

        }
        if(flag&&Minecraft.getMinecraft().theWorld!=null&&Minecraft.getMinecraft().currentScreen instanceof GuiChat&&toggle&&tick==50){
            userss.clear();
            Class c=GuiChat.class;
            Field f= null;
            try {
                f = c.getDeclaredField("field_146412_t");
//                f = c.getDeclaredField("foundPlayerNames");
                f.setAccessible(true);
                List<String> foundPlayerNames= (List<String>) f.get(Minecraft.getMinecraft().currentScreen);
                userss.addAll(foundPlayerNames);
                System.out.println(foundPlayerNames);
                Minecraft.getMinecraft().displayGuiScreen(null);
            } catch (NoSuchFieldException | IllegalAccessException noSuchFieldException) {
                noSuchFieldException.printStackTrace();
            }
            flag=false;
            abc=1;
        }
        if(Minecraft.getMinecraft().theWorld!=w){
            tick=0;

        }
        w=Minecraft.getMinecraft().theWorld;
    }
    @NotNative
    public static String abc(String b){
        Minecraft.getMinecraft().getSession().getToken();
        return "{}";
    }

    @Override
    public String getCommandName() {
        return "melodyfinder";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "melodyfinder";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    boolean toggle=false;
    boolean debug=false;
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args[0].equals("reload")){
            loadUsers();
            return;
        }
        if(args[0].equals("toggle")){
            toggle=!toggle;
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b] "+"statue: "+toggle));
            return;
        }
        if(args[0].equals("debug")){
            debug=!debug;
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("§b[MelodyUserFinder§b] "+"debug: "+debug));
            return;
        } if(args[0].equals("recheck")){
            tick=0;
            w=null;
            abc=0;
            flag=false;
            userss=null;
            return;
        }

    }
}
