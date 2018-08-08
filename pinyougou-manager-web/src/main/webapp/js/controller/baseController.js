/**
 * 公共angular的控制层
 * @Author Giovani
 * @Create: 2018/8/7 14:36
 */

app.controller('baseController', function ($scope) {

    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1, // 当前页码
        totalItems: 10, // 总条数
        itemsPerPage: 10, // 当前页查询条数
        perPageOptions: [10, 20, 30, 40, 50], // 页码选项，当前页查询条数选项
        onChange: function () { // 更改页面时触发时间
            $scope.reloadList();//重新加载
        }
    };

    // 重新加载列表数据
    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    // 选中的id集合
    $scope.selectIds = [];

    // 选中复选框，更新集合
    $scope.updateSelection = function (id, $event) {

        if ($event.target.checked) { // 该复选框被选中
            $scope.selectIds.push(id); // 添加到id集合
        } else {
            var idx = $scope.selectIds.indexOf(id); // 获取该值得索引
            $scope.selectIds.splice(idx, 1); // 根据索引从集合删除一个数据
        }
    }

    //提取json字符串数据中某个属性，返回拼接字符串 逗号分隔
    $scope.jsonToString = function (jsonString, key) {
        var json = JSON.parse(jsonString);//将json字符串转换为json对象
        var value = "";
        for (var i = 0; i < json.length; i++) {
            if (i > 0) {
                value += ","
            }
            value += json[i][key];
        }
        
        return value;
    }

});

