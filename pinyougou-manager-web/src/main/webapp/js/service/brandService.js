/**
 * 品牌管理angularjs服务层
 * @Author Giovani
 * @Create: 2018/8/7 14:31
 */

// 定义服务层
app.service('brandService', function ($http) {

    // 读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get("../brand/findAll.do");
    };

    // 分页查询品牌数据
    this.findPage = function (page, rows) {
        return $http.get('../brand/findPage.do?page=' + page + '&rows=' + rows);
    }

    // 增加品牌
    this.add = function (entity) {
        return $http.post('../brand/add.do', entity);
    }

    // 更新品牌
    this.update = function (entity) {
        return $http.post('../brand/update.do', entity);
    }

    // 根据id查询品牌信息
    this.findOne = function (id) {
        return $http.get('../brand/findOne.do?id=' + id)
    }

    // 批量删除选中的条目
    this.dele = function (selectIds) {
        return $http.get('../brand/delete.do?ids=' + selectIds);
    }

    // 根据条件分页查询品牌数据
    this.search = function (page, rows, searchEntity) {
        return $http.post('../brand/search.do?page=' + page + "&rows=" + rows, searchEntity);
    }


});