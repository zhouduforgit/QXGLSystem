package com.duyi.qxgl.action;

import com.duyi.qxgl.domain.Fn;
import com.duyi.qxgl.service.FnService;
import mymvc.ResponseBody;

import java.util.List;

public class FnAction {

    private FnService service = FnService.getService() ;
    @ResponseBody
    public List<Fn> list(){
        //查询所有的功能信息
        List<Fn> fns = service.findAll() ;
        return fns ;
    }

    public void save(Fn fn){
        service.save(fn);
    }

}
