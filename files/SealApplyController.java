package com.gomain.makeseal.sealapply.controller;


import com.gomain.makeseal.bean.Results;
import com.gomain.makeseal.common.ErrorCode;
import com.gomain.makeseal.common.ParamsUtil;
import com.gomain.makeseal.common.ResultConstant;
import com.platform.sysnumber.SysNumberService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 制章申请
 * @author WangChengyu
 * 2018/5/24 11:07
 */
@RestController
public class SealApplyController {

    Logger log = LoggerFactory.getLogger(SealApplyController.class);

    @Autowired
    SysNumberService sysNumberService;

    @Value("${makeseal.applynum.pre:test}")
    String applyPre;

    /**
     * 获得申请单编号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/num/getSealApplyNum", method = RequestMethod.POST)
    public Object getSealApplyNum(HttpServletRequest request, HttpServletResponse response) {

        String test = sysNumberService.getNumber(applyPre);
        log.info(test);

        return new Results<String>(0, ResultConstant.DEFAULT_MESSAGE, test);
    }


    /**
     * 提交补发申请单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/apply/reissueApply", method = RequestMethod.POST)
    public Object reissueApply(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }

    /**
     * 提交撤销申请单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/apply/revokeApply", method = RequestMethod.POST)
    public Object revokeApply(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }
    /**
     * 提交挂失申请单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/apply/lossLockApply", method = RequestMethod.POST)
    public Object lossLockApply(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }

    /**
     * 提交修改申请单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/apply/modifySealApply", method = RequestMethod.POST)
    public Object modifySealApply(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }

    /**
     * 获取指定的印章数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/apply/getSealRecord", method = RequestMethod.POST)
    public Object getSealRecord(HttpServletRequest request, HttpServletResponse response) {



        return null;
    }


        /**
         * 验证印章使用单位
         * @param request
         * @param response
         * @return
         */
    @RequestMapping(value = "/apply/verifySealUseUnit", method = RequestMethod.POST)
    public Object verifySealUseUnit(HttpServletRequest request, HttpServletResponse response) {
        // check params of necessary
        String useUnitCode = request.getParameter("useUnitCode");
        String useUnitName = request.getParameter("useUnitName");
        String useUnitType = request.getParameter("useUnitType");
        String useUnitNum = request.getParameter("useUnitNum");
        String approveUnitCode = request.getParameter("approveUnitCode");
        String approveUnitName = request.getParameter("approveUnitName");
        if(ParamsUtil.checkNull(useUnitCode, useUnitName, useUnitType, useUnitNum, approveUnitCode, approveUnitName)){
            return new Results<>(ErrorCode.FAILURE, ResultConstant.PARAMS_NULL,null);
        }
        // todo: 校验关系

        return null;
    }

    /**
     * 增加印章制作申请
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/apply/addSealApply", method = RequestMethod.POST)
    public Object addSealApply(HttpServletRequest request, HttpServletResponse response) {
        // check params of necessary
        String useUnitCode = request.getParameter("useUnitCode");
        String useUnitName = request.getParameter("useUnitName");
        String useUnitType = request.getParameter("useUnitType");
        String useUnitLegalPerson = request.getParameter("useUnitLegalPerson");
        String useUnitLegalPersonID = request.getParameter("useUnitLegalPersonID");
        String mspUniqueCode = request.getParameter("mspUniqueCode");
        if(ParamsUtil.checkNull(useUnitCode, useUnitName, useUnitType, useUnitLegalPerson,useUnitLegalPersonID, mspUniqueCode)){
            return new Results<>(ErrorCode.FAILURE, ResultConstant.PARAMS_NULL,null);
        }
        // todo: 校验法人库

        // todo：
        // 执行印章申请的添加
        log.info("------make-seal apply add record-------");

        // todo: 印章子表的请求参数的获取
        String childList = request.getParameter("childList");
        if(!ParamsUtil.checkNull(childList)){
            try {
                JSONArray jsonArray = JSONArray.fromObject(childList);//获得数组json
                List<Map<String, String>> list = (List)jsonArray;// 转换为map<String, String>
                log.info("sealApplyChild.size = "+list.size());




            }catch (Exception e){
                e.printStackTrace();
            }
        }


        return new Results<>(0, ResultConstant.DEFAULT_MESSAGE, "test");
    }




    //处理文件上传
    @RequestMapping(value = "/files/uploadBusinessLicense", method = RequestMethod.POST)
    public @ResponseBody Object uploadBusinessLicense(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = "temp";// todo: 上传到的文件路径
        try {

            // todo: file upload
        } catch (Exception e) {
            e.printStackTrace();
            return new Results<>(ErrorCode.FAILURE, ResultConstant.DEFAULT_EXCEPTION, null);
        }
        return new Results<>(ErrorCode.SUCCESS, ResultConstant.DEFAULT_SUCCESS, "test");
    }



}
