package mainframe;


import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * 播放音乐的面板
 * @author LanciLex
 * @create 2022-05-26 21:23
 */

public class MusicPlay extends JPanel implements ActionListener {

    AudioClip clip;
    JButton playButton, stopButton;

    public MusicPlay()  {
        setLayout(new FlowLayout());
        playButton = new JButton("音乐播放");
        stopButton = new JButton("音乐停止");
        playButton.addActionListener(this);
        stopButton.addActionListener(this);
        add(playButton);
        add(stopButton);
        File file=new File("music\\1.wav");//发现问题：文件大小大于1M的音频文件无法播放
        URI uri=file.toURI();
        URL url= null;
        try {
            url = uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        clip= Applet.newAudioClip(url);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== playButton)
            clip.loop();//循环播放
        else if(e.getSource()== stopButton)
            clip.stop();//暂停播放
    }
}
