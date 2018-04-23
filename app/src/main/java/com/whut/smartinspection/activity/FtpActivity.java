package com.whut.smartinspection.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.whut.smartinspection.R;
import com.whut.smartinspection.widgets.CustomToolBar;

import java.io.File;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;

/**
 * Created by wang on 2018/3/23.
 */

public class FtpActivity extends Activity {
    //    private static final String HOST = "123.206.32.149";
    private static final String HOST = "118.89.42.169";
    private static final int PORT = 21;
    //private String USERNAME = "ftptest";//ftpname
    private String USERNAME = "ftpname";
    private String PASSWORD = "WHUTjxdl2018";//
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String strSend;

    private FTPClient client;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x001) {
                adapter.add((String) msg.obj);
            } else if (msg.what == 0x002) {
                Toast.makeText(FtpActivity.this,
                        "connect fail", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftp_test);
        Log.i("USERNAME", USERNAME);
        Log.i("PASSWORD", PASSWORD);
        CustomToolBar.goBack(FtpActivity.this);
        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        Intent intent = getIntent();
        String type=intent.getStringExtra("name");
        switch (type){
            case "检测" :
                //加载文件列表
                adapter.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client = new FTPClient();
                        try {
                            client.connect(HOST, PORT);
                            client.login(USERNAME, PASSWORD);
                            client.changeDirectory("jiance");
                            String[] file = client.listNames();
                            for (int i = 0; i < file.length; i++) {
                                Log.i("file", file[i]);
                                Message message = handler.obtainMessage(0x001, file[i]);
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(0x002);
                            return;
                        }
                    }
                }).start();
                break;
            case "检修" :
                //加载文件列表
                adapter.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client = new FTPClient();
                        try {
                            client.connect(HOST, PORT);
                            client.login(USERNAME, PASSWORD);
                            client.changeDirectory("jianxiu");//进入相对路径
                            String[] file = client.listNames();
                            for (int i = 0; i < file.length; i++) {
                                Log.i("file", file[i]);
                                Message message = handler.obtainMessage(0x001, file[i]);
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(0x002);
                            return;
                        }
                    }
                }).start();
                break;
            case "评价" :
                //加载文件列表
                adapter.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client = new FTPClient();
                        try {
                            client.connect(HOST, PORT);
                            client.login(USERNAME, PASSWORD);
                            client.changeDirectory("pingjia");//进入相对路径
                            String[] file = client.listNames();
                            for (int i = 0; i < file.length; i++) {
                                Log.i("file", file[i]);
                                Message message = handler.obtainMessage(0x001, file[i]);
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(0x002);
                            return;
                        }
                    }
                }).start();
                break;
            case "验收" :
                //加载文件列表
                adapter.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client = new FTPClient();
                        try {
                            client.connect(HOST, PORT);
                            client.login(USERNAME, PASSWORD);
                            client.changeDirectory("yanshou");//进入相对路径
                            String[] file = client.listNames();
                            for (int i = 0; i < file.length; i++) {
                                Log.i("file", file[i]);
                                Message message = handler.obtainMessage(0x001, file[i]);
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(0x002);
                            return;
                        }
                    }
                }).start();
                break;
            case "运维" :
                //加载文件列表
                adapter.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client = new FTPClient();
                        try {
                            client.connect(HOST, PORT);
                            client.login(USERNAME, PASSWORD);
                            client.changeDirectory("yunwei");//进入相对路径
                            String[] file = client.listNames();
                            for (int i = 0; i < file.length; i++) {
                                Log.i("file", file[i]);
                                Message message = handler.obtainMessage(0x001, file[i]);
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(0x002);
                            return;
                        }
                    }
                }).start();
                break;
            case "其他" :
                //加载文件列表
                adapter.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client = new FTPClient();
                        try {
                            client.connect(HOST, PORT);
                            client.login(USERNAME, PASSWORD);
                            client.changeDirectory("qita");//进入相对路径
                            String[] file = client.listNames();
                            for (int i = 0; i < file.length; i++) {
                                Log.i("file", file[i]);
                                Message message = handler.obtainMessage(0x001, file[i]);
                                handler.sendMessage(message);
                            }
                        } catch (Exception e) {
                            handler.sendEmptyMessage(0x002);
                            return;
                        }
                    }
                }).start();
                break;
            default:
                break;

        }
//        //加载文件列表
//        adapter.clear();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                client = new FTPClient();
//                try {
//                    client.connect(HOST, PORT);
//                    client.login(USERNAME, PASSWORD);
//                    String[] file = client.listNames();
//                    for (int i = 0; i < file.length; i++) {
//                        Log.i("file", file[i]);
//                        Message message = handler.obtainMessage(0x001, file[i]);
//                        handler.sendMessage(message);
//                    }
//                } catch (Exception e) {
//                    handler.sendEmptyMessage(0x002);
//                    return;
//                }
//            }
//        }).start();


        /**
         * commons-net-3.0.1.jar
         * listNames返回NULL,list返回Int,listFiles返回NULL
         * 因为传进去的参数是(String)null
         */

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                Toast.makeText(FtpActivity.this, "正在下载.....请等待！", Toast.LENGTH_LONG).show();
                String dir = Environment.getExternalStorageDirectory()
                        + "/test/";
                File fileDir = new File(dir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                final String path = dir + adapter.getItem(position);
                final File file = new File(path);
                if (file.exists()) {
                    file.delete();
                    Log.i("delete", "original file deleted");
                }
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // 参考/doc/manual.en.html，最后面的参数是监听器
                                client.download(adapter.getItem(position),file, new MyTransferListener());
//                                //调用第三方软件打开PDF
//                                Intent intent=getWordFileIntent(file);
//                                startActivity(intent);

                                //打开pdfviewer,将path作为参数
                                Intent intent1 = new Intent(FtpActivity.this, PdfViewActivity.class);
                                intent1.putExtra("path",path);
                                startActivity(intent1);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //android获取一个用于打开pdf文件的intent
    public static Intent getWordFileIntent(File file )
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    public class MyTransferListener implements FTPDataTransferListener {
        public void started() {
            Log.i("download", "download start");
        }

        public void transferred(int length) {
            Log.i("download", "have download " + length + " bytes");
        }

        public void completed() {
            Log.i("download", "download completed");
        }

        public void aborted() {
            Log.i("download", "download aborted");
        }

        public void failed() {
            Log.i("download", "download failed");
        }
    }
}
