package cheanxin.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by 向麒 on 2017/1/12.
 */
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {



    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam(value = "file", required = false) MultipartFile file,Errors errors) {
        //获取文件扩展名
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        //获取文件名
        String videoName=file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes(); //将文件转换成字节流形式
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用上传文件的具体方法
//        String videoPath=uploadFile(bytes,ext_Name);

//        return new ResponseEntity<>(deptService.save(dept, parentDept), HttpStatus.CREATED);
        return null;
    }


//    /*
//     * 利用字节流上传文件
//     */
//    public String uploadFile(byte[] byteFile, String ext_file) {
//        // 拼接服务区的文件路径
//        StringBuffer sbPath = new StringBuffer();
//        sbPath.append("http://192.168.22.252");
//        try {
//            // 初始化文件资源
//            ClientGlobal
//                    .init("F:\\project\\dmsd-itoo-video\\wanghongyun-video-0817\\dmsd-itoo-video-parent\\dmsd-itoo-video-web\\src\\main\\resources\\fdfs_client.conf");
//
//            // 链接FastDFS服务器，创建tracker和Stroage
//            TrackerClient trackerClient = new TrackerClient();
//            TrackerServer trackerServer = trackerClient.getConnection();
//            StorageServer storageServer = null;
//            StorageClient storageClient = new StorageClient(trackerServer,
//                    storageServer);
//
//            //利用字节流上传文件
//            String[] strings = storageClient.upload_file(byteFile, ext_file, null);
//
//            for (String string : strings) {
//                sbPath.append("/" + string);
//                System.out.println(string);
//            }
//            // 全路径
//            System.out.println(sbPath);
//        } catch (IOException | MyException e) {
//            e.printStackTrace();
//        }
//        return sbPath.toString();
//    }
}
