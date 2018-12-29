<!DOCTYPE html>
<html lang="en" xmlns="">
<head>
    <meta charset="utf-8">
    <title>Vue 测试实例 - 菜鸟教程(runoob.com)</title>
</head>
<style>
    .class{
        background: #444;
        color: #eee;
    }
    img{
        width: 50px;
        height: 50px;
    }
</style>
<body>
<script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
<div id="app">
    <div v-html="message0"></div>
    <label for="r1">修改颜色</label>
    <input type="checkbox" v-model="class1" id="r1"><br>
    <div v-bind:class="{'class': class1}">
        v-bind:class 指令
    </div>
    <input placeholder="请输入信息" v-model="message3">
    <div v-if="view">Watching!</div>
    <a v-bind:href="url" v-bind:target="target">百度页面</a><br>
    <a :href="url" v-bind:target="target">百度页面</a><br><!--v-bind可省略-->
    {{ message1 }}<br>
    {{ 5+5 }}<br>
    {{ ok ? 'YES' : 'NO' }}<br>
    {{ message2.split('').reverse().join('') | capitalize}}
    <div v-bind:id="'list-' + id">该div的id为list-1</div>
    <a v-on:click="click">弹出弹窗</a><br>
    <a @click="click">弹出弹窗</a><br><!--v-on可省略-->
    <button v-on:click="reverseMessage">反转字符串</button><br>
    {{Num}}
    <div v-if="Num % 1 >= 0.7">数字大于0.7</div>
    <div v-else-if="Num % 1 >= 0.3">数字大于0.3小于0.7</div>
    <div v-else>数字小于0.3</div>
    <input type="text" class="edit" placeholder="判断输入的字符长度" v-model="inp0">
    <label v-if="calLen(inp0)>10">输入的字符长度大于10</label>
    <label v-else-if="calLen(inp0)>5">输入的字符长度大于5</label>
    <label v-else-if="calLen(inp0)>0">输入了字符</label>
    <label v-else>没有输入字符</label>
    <br>
    <div>
        <img src="https://static.runoob.com/images/mix/Vue-c1404f26d2d428847a0dbbfe214914c207716c77/asset/img/ic_user.png" v-show="calLen(inp0)<=0">
    </div>
    <input type="text" class="edit" placeholder="判断输入的字符难度" v-model="inp1">
    <label v-if="checkKey(inp1)===4" v-bind:style="{ color: colors }">极难</label>
    <label v-else-if="checkKey(inp1)===3" v-bind:style="{ color: colors }">难</label>
    <label v-else-if="checkKey(inp1)===2" v-bind:style="{ color: colors }">中</label>
    <label v-else-if="checkKey(inp1)===1" v-bind:style="{ color: colors }">易</label>
    <label v-else v-bind:style="{ color: colors }">请输入字符</label>
    <br>
</div>
<script>
    new Vue({
        el: '#app',
        data:{
            //以下判断 class1 的值，如果为 true 使用 class类的样式，否则不使用该类
            class1: false,
            view: true,
            message0: "<a href='http://www.baidu.com' target='_blank'>百度</a>",
            url: "http://www.baidu.com",
            target: "_blank",
            message1: "学习Vue",
            ok: true,
            message2: "abcdefg",
            id: 1,
            message3: "",
            Num: Math.random(),
            inp0: "",
            inp1: ""
        },
        methods: {
            click: function () {//点击弹窗
                alert(1);
            },
            reverseMessage: function () {//将字符串反转
                this.message2 = this.message2.split('').reverse().join('');
            },
            calLen: function (n) {//返回字符长度
                if (!n) {
                    return 0;
                } else {
                    return n.split('').length;
                }
            },
            checkKey: function (n) {//返回字符难度
                var p1 = (n.search(/[a-z]/) !== -1) ? 1 : 0;
                var p2 = (n.search(/[0-9]/) !== -1) ? 1 : 0;
                var p3 = (n.search(/[A-Z]/) !== -1) ? 1 : 0;
                var p4 = (n.search(/[,./<>?;':"{}|~`!@#$%^&*()_+=]/) !== -1) ? 1 : 0;
                var p5 = p1 + p2 + p3 + p4;
                switch (p5) {
                    case 0:
                        colors = "#00F";
                        break;
                    case 1:
                        colors = "#0F0";
                        break;
                    case 2:
                        colors = "#F00";
                        break;
                    case 3:
                        colors = "#0FF";
                        break;
                    case 4:
                        colors = "#F0F";
                        break;
                    default:
                        colors = "#FF0";
                        break;
                }
                return p5;
            }
        },
        filters: {//过滤器函数:对输入的字符串第一个字母转为大写
            capitalize: function (value) {
                if (!value) return '';
                value = value.toString();
                return value.charAt(0).toUpperCase() + value.slice(1);
            }
        }
    });
</script>
</body>
</html>