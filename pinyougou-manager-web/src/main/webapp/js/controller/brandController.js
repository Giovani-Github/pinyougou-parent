/**
 * 品牌管理控制层
 * @Author Giovani
 * @Create: 2018/8/7 14:43
 */

// 定义pinyougou模块的控制层
app.controller('brandController', function ($scope, $controller, brandService) {

    $controller('baseController', {$scope: $scope});//继承

    // 读取列表数据绑定到表单中
    $scope.findAll = function () {
        brandService.findAll().success(function (response) {
            // 把服务器返回的数据绑定到页面的list变量中，原理：angularjs的双向绑定
            $scope.list = response;
        });
    }

    // 分页查询品牌数据
    $scope.findPage = function (page, rows) {
        brandService.findPage(page, rows).success(function (response) {
            $scope.list = response.rows;
            // 更新总记录数
            $scope.paginationConf.totalItems = response.total;
        });
    }

    // 根据id查询品牌信息
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity = response;
        })
    }

    // 保存品牌
    $scope.save = function () {

        var serviceObject; // 服务层对象

        if ($scope.entity.id != null) {
            serviceObject = brandService.update($scope.entity);
        } else {
            serviceObject = brandService.add($scope.entity);
        }

        serviceObject.success(function (response) {

            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }

        });
    }

    // 批量删除选中的条目
    $scope.dele = function () {
        brandService.dele($scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        });
    }

    // 初始化搜索对象
    $scope.searchEntity = {};

    // 根据条件分页查询品牌数据
    $scope.search = function (page, rows) {
        brandService.search(page, rows, $scope.searchEntity).success(function (response) {
            $scope.list = response.rows;
            // 更新总记录数
            $scope.paginationConf.totalItems = response.total;
        });
    }

});