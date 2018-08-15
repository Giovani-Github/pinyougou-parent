/**
 * 不带分页的angularjs模板
 * @Author Giovani
 * @Create: 2018/8/7 14:32
 */

// 定义angularjs模块
var app = angular.module('pinyougou', []);

/**
 * 我们测试后发现高亮显示的html代码原样输出，这是angularJS为了防止html攻击采取的安全机制。
 * 我们如何在页面上显示html的结果呢？我们会用到$sce服务的trustAsHtml方法来实现转换。
 */
/*$sce服务写成过滤器*/
app.filter('trustHtml', ['$sce', function ($sce) {
    return function (data) {
        return $sce.trustAsHtml(data);
    }
}]);
