package com.mysoft.alpha.controller;

import com.mysoft.alpha.entity.AdminRole;
import com.mysoft.alpha.exception.CustomException;
import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import com.mysoft.alpha.service.AdminPermissionService;
import com.mysoft.alpha.service.AdminRoleMenuService;
import com.mysoft.alpha.service.AdminRolePermissionService;
import com.mysoft.alpha.service.AdminRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/role")
public class RoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    @GetMapping("/list")
    public Result listRoles() throws CustomException {
        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermsAndMenus());
    }

    /**
     * 根据当前用户查询role列表
     *
     * @return
     */
    @GetMapping("/listbyuser")
    public Result listRolesByCurrUser()  throws CustomException {
//        return ResultFactory.buildSuccessResult(adminRoleService.listWithPermsAndMenus());
        System.out.println("---------------------------------------");
        String username =SecurityUtils.getSubject().getPrincipal().toString();
        List<AdminRole> list =   adminRoleService.listSubRolesByUser(username);
        System.out.println("---------------------------------------"+list.size());
        return ResultFactory.buildSuccessResult(list);
    }

    @PutMapping("/status")
    @Transactional
    public Result updateRoleStatus(@RequestBody AdminRole requestRole)  throws CustomException {
        AdminRole adminRole = adminRoleService.updateRoleStatus(requestRole);
        String message = "用户" + adminRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/edit")
    @Transactional
    public Result editRole(@RequestBody AdminRole requestRole)  throws CustomException {
        adminRoleService.addOrUpdate(requestRole);
        adminRolePermissionService.savePermChanges(requestRole.getId(), requestRole.getPerms());
        return ResultFactory.buildSuccessResult("修改角色信息成功");
    }


    @PostMapping("/add")
    @Transactional
    public Result addRole(@RequestBody AdminRole requestRole)  throws CustomException {
        adminRoleService.editRole(requestRole);
        return ResultFactory.buildSuccessResult("增加角色信息成功");
    }

    @GetMapping("/perm")
    public Result listPerms() throws CustomException  {
        return ResultFactory.buildSuccessResult(adminPermissionService.list());
    }

    @PutMapping("/menu")
    @Transactional
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds)  throws CustomException {
        adminRoleMenuService.updateRoleMenu(rid, menusIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }
}
