package com.duyi.qxgl.action;

import com.duyi.qxgl.domain.Page;
import com.duyi.qxgl.service.RoleService;
import mymvc.RequestParam;
import mymvc.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RoleAction {

    private RoleService service = RoleService.getService() ;

    @ResponseBody
    public Page list(@RequestParam("page") Integer page ,@RequestParam("rows") Integer rows){
        Page p = service.list(page,rows) ;
        return p ;
    }

    @ResponseBody
    public String setFns(@RequestParam("rid") Integer rid, HttpServletRequest request){
        String[] fidArray = request.getParameterValues("fid") ;
        service.setFns(rid,fidArray);
        return "功能分配成功";
    }

    @ResponseBody
    public List<Integer> findFidsByRole(@RequestParam("rid") Integer rid){
        return service.findFidsByRole(rid);
    }
}
