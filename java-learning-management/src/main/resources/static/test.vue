<!DOCTYPE html>
<html lang="en" xmlns="">
<head>
    <meta charset="utf-8">
    <title>Vue 测试</title>
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/velocity/1.2.3/velocity.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/animate.css@3.5.1" rel="stylesheet" type="text/css">
</head>
<style>
    .class {
        background: #444;
        color: #eee;
    }

    #img0 {
        width: 50px;
        height: 50px;
    }

    #table0 {
        border: 1px solid black;
        width: 100%;
    }

    .th0 {
        height: 50px;
    }

    .th0, .td0 {
        border-bottom: 1px solid #ddd;
    }

    /*过渡1*/
    /* 可以设置不同的进入和离开动画 */
    /* 设置持续时间和动画函数 */
    .fade-enter-active, .fade-leave-active {
        transition: opacity 2s
    }

    .fade-enter, .fade-leave-to /* .fade-leave-active, 2.1.8 版本以下 */
    {
        opacity: 0
    }

    /*过渡2*/
    /* 可以设置不同的进入和离开动画 */
    /* 设置持续时间和动画函数 */
    .slide-fade-enter-active {
        transition: all .3s ease;
    }

    .slide-fade-leave-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .slide-fade-enter, .slide-fade-leave-to
        /* .slide-fade-leave-active 用于 2.1.8 以下版本 */
    {
        transform: translateX(10px);
        opacity: 0;
    }

    /*动画1*/
    .bounce-enter-active {
        animation: bounce-in .5s;
    }

    .bounce-leave-active {
        animation: bounce-in .5s reverse;
    }

    @keyframes bounce-in {
        0% {
            transform: scale(0);
        }
        50% {
            transform: scale(1.5);
        }
        100% {
            transform: scale(1);
        }
    }
</style>
<body>
<div id="app">
    <h3>{{vueBase}}</h3>
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
    <button v-on:click="reverseMessage">反转字符串</button>
    <br>
    <h3>{{vueIf}}</h3>
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
        <img id="img0"
             src="https://static.runoob.com/images/mix/Vue-c1404f26d2d428847a0dbbfe214914c207716c77/asset/img/ic_user.png"
             v-show="calLen(inp0)<=0" alt="没有输入字符">
    </div>
    <input type="text" class="edit" placeholder="判断输入的字符难度" v-model="inp1">
    <!--用在表单控件上的，用于实现双向数据绑定，所以如果用在除了表单控件以外的标签是没有任何效果的-->
    <label v-if="checkKey(inp1)===4" v-bind:style="{ color: colors }">极难</label>
    <label v-else-if="checkKey(inp1)===3" v-bind:style="{ color: colors }">难</label>
    <label v-else-if="checkKey(inp1)===2" v-bind:style="{ color: colors }">中</label>
    <label v-else-if="checkKey(inp1)===1" v-bind:style="{ color: colors }">易</label>
    <label v-else v-bind:style="{ color: colors }">请输入字符</label>
    <h3>{{vueLoop}}</h3>
    <div>
        <ul>
            <template v-for="message in messages0">
                <li>{{ message }}</li>
            </template>
        </ul>
        --------------------
        <ol>
            <li v-for="message in messages1">{{ message.value }}</li>
        </ol>
        --------------------
        <ul>
            <li v-for="value in object">
                {{ value }}
            </li>
        </ul>
        --------------------
        <ul>
            <li v-for="(value, key) in object">
                {{ key }} : {{ value }}
            </li>
        </ul>
        ----------默认升序排序----------
        <ul>
            <li v-for="value in object0">
                {{ value }}
            </li>
        </ul>
        ----------循环嵌套----------
        <ul>
            <li v-for="(value,key) in object1">
                <p v-if="typeof value !== 'object'"> {{value}}</p>
                <p v-if="typeof value == 'object'"> {{key}}</p>
                <ul v-if="typeof value == 'object'">
                    <li v-for="(value, key) in value">
                        {{key}}:{{value}}
                    </li>
                </ul>
            </li>
        </ul>
        ----------九九乘法表----------
        <div v-for="n in 9">
            <b v-for="m in n">
                {{m}}*{{n}}={{m*n}}
            </b>
        </div>
    </div>
    <h3>{{vueComputed}}(略)</h3>
    <h3>{{vueWatch}}(略)</h3>
    <div id="computed_props">
        千米 :
        <label>
            <input type="text" v-model="kilometers">
        </label>km<->
        米 :
        <label>
            <input type="text" v-model="meters">
        </label>m
    </div>
    <p id="info">修改前值为: ，修改后值为: </p>
    <table id="table0">
        <tr>
            <th class="th0">序号</th>
            <th class="th0">商品名称</th>
            <th class="th0">商品价格</th>
            <th class="th0">购买数量</th>
            <th class="th0">操作</th>
        </tr>
        <tr v-for="iphone in Ip_Json">
            <td class="td0">{{ iphone.id }}</td>
            <td class="td0">{{ iphone.name }}</td>
            <td class="td0">{{ iphone.price }}</td>
            <td class="td0">
                <button :disabled="iphone.count === 0" @click="iphone.count-=1">-</button>
                {{ iphone.count }}
                <button @click="iphone.count+=1">+</button>
            </td>
            <td class="td0">
                <button @click="iphone.count=0">移除</button>
            </td>
        </tr>
    </table>
    总价：${{totalPrice()}}
    <h3>{{vueCheckBox}}</h3>
    <p>
        全选：
    </p>
    <input type="checkbox" id="checkbox" v-model="checked" @change="changeAllChecked()">
    <label for="checkbox">
        {{checked}}
    </label>
    <p>
        多个复选框：
    </p>
    <input type="checkbox" id="runoob" value="Runoob" v-model="checkedNames">
    <label for="runoob">
        Runoob
    </label>
    <input type="checkbox" id="google" value="Google" v-model="checkedNames">
    <label for="google">
        Google
    </label>
    <input type="checkbox" id="taobao" value="Taobao" v-model="checkedNames">
    <label for="taobao">
        taobao
    </label>
    <br>
    <span>
		选择的值为:{{checkedNames}}
	</span>
    <h3>{{vueComponent}}</h3>
    <div>
        <label>
            <input v-model="parentMsg">
        </label>
        <br>
        <child :message="parentMsg"></child>
    </div>
    <div>
        <p>{{ total }}</p>
        <button-counter @increment="incrementTotal"></button-counter>
        <button-counter @increment="incrementTotal"></button-counter>
    </div>
    <h3>{{vueDesignTemin}}</h3>
    <p>页面载入时，input 元素自动获取焦点：</p>
    <label>
        <input v-focus>
    </label>
    <h3>{{vueTransition}}</h3>
    <button @click="show = !show">click</button>
    <br>
    <transition name="fade">
        <p v-show="show" :style="styleObj">过渡实例1</p>
    </transition>
    <transition name="slide-fade">
        <p v-if="show">过渡实例2</p>
    </transition>
    <transition name="bounce">
        <p v-if="show">动画示例1</p>
    </transition>
    <transition name="custom-classes-transition" enter-active-class="animated tada"
                leave-active-class="animated bounceOutRight">
        <p v-if="show">动画示例2</p>
    </transition>
    <transition v-on:before-enter="beforeEnter" v-on:enter="enter" v-on:leave="leave" v-bind:css="false">
        <p v-if="show">动画示例3</p>
    </transition>
</div>
</body>
</html>
<script>
    Vue.component('child', {
        // 声明 props
        // prop 是单向绑定的：当父组件的属性变化时，将传导给子组件，但是不会反过来
        props: ['message'],
        // 同样也可以在 vm 实例中像 “this.message” 这样使用
        template: '<span>{{ message }}</span>'
    });
    Vue.component('button-counter', {
        template: '<button v-on:click="incrementHandler">{{ counter }}</button>',
        data: function () {
            return {
                counter: 0
            }
        },
        methods: {
            incrementHandler: function () {
                this.counter += 1;
                this.$emit('increment');/*$emit("FunctionName",[arg1,arg2...])触发父组件方法*/
            }
        },
    });
    var vm = new Vue({
        el: '#app',
        data: {
            vueBase: "Vue基础",
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
            vueIf: "条件语句",
            Num: Math.random(),
            inp0: "",
            inp1: "",
            vueLoop: "循环语句",
            messages0: [
                0,
                1,
                "a",
                "b",
                "aa",
                "bb"
            ],
            messages1: [
                {value: 0},
                {value: 1},
                {value: "a"},
                {value: "b"},
                {value: "aa"},
                {value: "bb"}
            ],
            object: {
                name: "菜鸟教程",
                url: "http://www.runoob.com",
                slogan: "学的不仅是技术，更是梦想！"
            },
            object0: {//会默认对属性进行升序排序输出
                0: "aaaaaaaa",
                4: "eeeeeeee",
                1: "bbbbbbbb",
                5: "ffffffff",
                3: "dddddddd",
                2: "cccccccc",
                7: "hhhhhhhh",
                6: "gggggggg"
            },
            object1: {
                name: "菜鸟教程",
                url: "http://www.runoob.com",
                slogan: "学的不仅是技术，更是梦想！",
                object2: {
                    x: "messageX",
                    y: "messageY",
                    z: "messageZ"
                }
            },
            vueComputed: "计算属性",
            vueWatch: "监听属性",
            kilometers: 0,
            meters: 0,
            Ip_Json: [
                {
                    id: 1,
                    name: 'iphone 8',
                    price: 5099,
                    count: 1
                },
                {
                    id: 2,
                    name: 'iphone xs',
                    price: 8699,
                    count: 1
                },
                {
                    id: 3,
                    name: 'iphone xr',
                    price: 6499,
                    count: 1
                }
            ],
            vueCheckBox: "复选框",
            checked: false,
            checkedNames: [],
            checkedArr: ["Runoob", "TaoBao", "Google"],
            vueComponent: "组件",
            parentMsg: "父组件内容",
            total: 0,
            vueDesignTemin: "自定义指令",
            vueTransition: "过渡 & 动画",
            show: true,
            styleObj: {
                fontSize: "15px",
                color: "#000",
            },
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
            },
            totalPrice: function () {
                var totalP = 0;
                for (var i = 0, len = this.Ip_Json.length; i < len; i++) {
                    totalP += this.Ip_Json[i].price * this.Ip_Json[i].count;
                }
                return totalP;
            },
            changeAllChecked: function () {
                if (this.checked) {
                    this.checkedNames = this.checkedArr
                } else {
                    this.checkedNames = []
                }
            },
            incrementTotal: function () {
                this.total += 1
            },
            beforeEnter: function (el) {
                el.style.opacity = 0;
                el.style.transformOrigin = 'left';
            },
            enter: function (el, done) {
                Velocity(el, {opacity: 1, fontSize: '1.4em'}, {duration: 300});
                Velocity(el, {fontSize: '1em'}, {complete: done});
            },
            leave: function (el, done) {
                Velocity(el, {translateX: '15px', rotateZ: '50deg'}, {duration: 600});
                Velocity(el, {rotateZ: '100deg'}, {loop: 2});
                Velocity(el, {
                    rotateZ: '45deg',
                    translateY: '30px',
                    translateX: '30px',
                    opacity: 0
                }, {complete: done})
            },
        },
        filters: {//过滤器函数:对输入的字符串第一个字母转为大写
            capitalize: function (value) {
                if (!value) return '';
                value = value.toString();
                return value.charAt(0).toUpperCase() + value.slice(1);
            }
        },
        computed: {//功能与methods相同，但是computed依赖缓存，可将结果进行缓存，性能会更好，但是占用缓存。
        },
        watch: {
            kilometers: function (val) {
                this.kilometers = val;
                this.meters = val * 1000;
            },
            meters: function (val) {
                this.kilometers = val / 1000;
                this.meters = val;
            },
            checkedNames: function () {
                this.checked = this.checkedNames.length === this.checkedArr.length;
            }
        },
        directives: {
            // 注册一个局部的自定义指令 v-focus
            focus: {
                // 指令的定义
                inserted: function (el) {
                    // 聚焦元素
                    el.focus()
                }
            }
        },
    });
    // $watch 是一个实例方法
    vm.$watch('kilometers', function (newValue, oldValue) {
        // 这个回调将在 vm.kilometers 改变后调用
        document.getElementById("info").innerHTML = "修改前值为: " + oldValue + "，修改后值为: " + newValue;
    });
</script>