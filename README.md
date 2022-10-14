# 24PointsGame

## 1、项目简介

已知一副扑克牌有54张，去除大王和小王，剩余52张。在其中抽取4张牌，利用加减乘除进行计算得到24，除法必须能够除尽。编写程序从一副扑克牌，选择4张，进行计算是否能得到24。如果可以，排序列出可能的计算表达式，可能有多种计算形式。

**实现功能**

- 在界面上显示出52张扑克牌。
- 通过点击的方式选出4张扑克牌并放置在界面某一个地方，位置自己确定。
- 如果可以计算出24，排序列出可能的计算方式，并显示在界面上，如果不能算出，请提示。
- APP界面自行设计，至少包含两个界面。
- 界面美观度，功能完整度，扩展性功能，程序稳定性。



## 2、项目实现

### 2.1、设计思想

**卡片选取功能的实现方法：**

“计算24点”上一共有56个ImageView，其中有52个显示扑克牌，按照扑克牌的花色以及点数有序排列。界面下方4个ImageView为卡片空位。当点击扑克牌时，设置ImageView的clickListener将当前所点击的卡片设置为隐藏状态，并将下方的空白卡片图片设置为所选择的扑克牌。

程序使用一个容量为4栈用于存储当前所选择的卡片。当点击“back”时弹出栈上方的卡片，并将弹出的卡片对应的ImageView设置显示，并将界面下方对应的卡片设置为空白卡片，即实现了卡片的退出功能。当点击“clear”时清空栈，并同时设置对应的ImageView，即实现清空卡片功能。点击“get 24pints”时调用“Point24”类的“get24pointequ”函数获取24点表达式，并显示在弹窗上。若无法组成24点表达式，便提示用户。

**获取24点表达式的算法思路：**

首先从4个数字中有序地选出2个数字，并选择加、减、乘、除 4 种运算操作之一，用得到的结果取代选出的2个数字，剩下3个数字。然后在剩下的3个数字中有序地选出2个数字，并选择 4 种运算操作之一，用得到的结果取代选出的2个数字，剩下2个数字。最后剩下2个数字，有2种不同的顺序，并选择4种运算操作之一。

因此，可以通过回溯的方法遍历所有不同的可能性。使用一个列表存储目前的全部数字，每次从列表中选出2个数字，再选择一种运算操作，用计算得到的结果取代选出的2个数字，这样列表中的数字就减少了1个。重复上述步骤，直到列表中只剩下1个数字，这个数字就是一种可能性的结果，如果结果等于24，则说明可以通过运算得到24。如果所有的可能性的结果都不等于24，则说明无法通过运算得到24。

除法运算为实数除法，因此结果为浮点数，列表中存储的数字也都是浮点数。在判断结果是否等于 24 时需要考虑精度误差，当误差小于10-6 时可以认为是相等。

进行除法运算时，除数不能为0，如果遇到除数为0的情况，则这种可能性可以直接排除。 

 

### 2.2、程序结构

**（A）Java.com.example.a24pointsgame目录为程序java代码**

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739846.png)

1. AboutMe.class.java ——“aboutme”界面的程序代码

2. GameActivity.java ——“game”界面的程序代码

3. Guide.java ——“guide”界面的程序代码

4. MainActivity.java ——“main”界面的程序代码

5. Point24.java ——负责计算24点表达式

 

**（B）24PointsGame\app\src\main\res\layout目录下为布局文件**

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739881.png)

1. activity_about_me.xml ——“aboutme”界面布局文件

2. activity_game.xml ——“game”界面布局文件
3. activity_guide.xml ——“guide”界面布局文件

4. activity_main.xml ——“main”界面布局文件

 

**（C）24PointsGame\app\src\main\res\values 目录下为资源文件**

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739883.png)

1. Colors.xml ——颜色资源文件

2. Strings.xml ——存放程序中的字符串

3. Themes.xml ——主题资源设置文件

   

**（D）24PointsGame\app\src\main\res\drawable 目录下为图片资源****

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739889.png)

 

### 2.3、功能关系图

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739892.png)

 

### 2.4、类的说明和类图

 该程序共包含4个边界类、1个实体类共5个类。

- “AboutMe”负责“aboutme”界面的生成以及配置；
- “MainActivity”负责主界面的生成、配置以及界面的跳转等工作；
- “Guide”负责“guide”界面的生成、配置；
- “GameActivity”负责“计算24点”界面的生成配置、卡片的选取、显示24点表达式等功能。
- “Point24”负责验证当前4张卡片能够组成24点表达式以及计算24点表达式。

UML类图如下所示：

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739898.jpg)

 

### 2.5、程序流程图

 该程序共有四个界面，分别是“主界面”、“aboutme”、“guide”、“24点计算界面”。界面之间的跳转如下图所示，“aboutme”、“guide”分别可以点击“back”按钮返回“主界面”。“主界面”可以跳转到其他界面，其为程序的入口界面。用户可以通过点击“24点计算界面”的扑克牌计算24点表达式。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739850.png)

“24点计算界面”的流程图如下，用户通过点击界面上的扑克牌，进行选择。也可以通过点击界面下面的“back”和“clear”按钮退回和清除选择的卡片，当用户选择4张卡片后点击“get 24pints”按钮获取24点表达式，若当前4张卡片不能组成24点表达式，则程序会提醒用户，反之将列出4张卡片所有的24点表达式。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141739862.png)

## 3、程序界面

### 3.1、开始界面

在此界面有三个按钮，分别是“play”，“guide”，“about me”。

点击“guide”按钮显示扑克牌计算24点app的使用方法以及相关说明；点击“about me”显示游戏开发作者相关信息；点击“play”按钮开始扑克牌24点计算界面。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141744924.png)

### 3.2、guide界面

此界面显示扑克牌计算24点app的使用方法以及相关说明。点击“back”按钮即可返回app主界面。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141744928.png)

### 3.3、about me界面

此界面显示app开发人员的相关信息，包括扑克牌计算24点app版本号，作者姓名、联系方式等信息。点击“back”按钮即可返回app主界面。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141744938.png)

### 3.4、扑克牌计算24点界面

用户可以通过点击界面上的扑克牌进行选择，选择出的扑克牌将放置在界面下端的空白处。用户也可以点击下端的“back”按钮退回上次选择的扑克牌，也可以点击“clear”按钮清空选择的扑克牌。当选择4张扑克牌后，用户则无法继续选择其他的扑克牌。

 

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141744940.png)

 

当用户选择4张扑克牌后，点击“get 24point”按钮，app将显示此4张扑克能够组成24点等式的所有表达式。用户可以通过点击“清空”按钮清空之前选择的扑克牌组合，也可以点击“确定”返回。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141744941.png)

 

若当前4张扑克不能够组成24点表达式app将提醒用户无法构成24点表达式，并提示重新选择扑克牌。

![img](https://xc-figure.oss-cn-hangzhou.aliyuncs.com/img/202210141744949.png)

 

## 4、总结与体会

通过学习开发扑克牌计算24点app，了解了android studio开发的基本原理。学习了android程序的运行过程。一个进程创建时系统会为它创建一个Activity（活动），紧接着调用onCreate()，onCreate()中主要是进行一些初始化，例如读取XML资源文件创建布局，设置主界面各种监听函数等等，每个进程都会调用onCreate()。

接着是onStart()，系统会自动调用；但是，当需要在程序中需要创建其他的activity的时候，需要显式调用这个onStart()，即startActivity(intent)，这里的intent表示一个意图，就是想创建的新Activity。

还学习了android的一些基本组件，例如：id，控件的唯一表示符，在同一个XML文件中不可重复，相当于身份证的作用，课用来寻找并且绑定此控件；layout_width以及layout_height，分别设置view的宽高；gravity指定当文本小于视图时如何通过视图的 x 和/或 y 轴对齐文本，常用的取值为（center_horizontal、center、fill_horizontal、fill_vertical）；typeface，用于设置文本的字体等

同时还掌握了android的一些布局方式例如FrameLayout（帧布局）放置的控件都只能罗列到左上角，控件会有重叠；LinearLayout(线性布局)可以通过orientation属性设置线性排列的方向是垂直还是纵向的,每行或每列只有一个元素；AbsoluteLayout（绝对布局）可以让子元素指定准确的x、y坐标值，并显示在屏幕上。Absolute Layout允许元素之间相互重叠；RelativeLayout（相对布局）可以以右对齐，或上下，或置于屏幕中央的形式来排列两个元素。元素按顺序排列，因此如果第一个元素在屏幕的中央，那么相对于这个元素的其他元素将以屏幕中央的相对位置来排列；TableLayout（表格布局）将以子元素的位置分配到行或列。

通过扑克牌24点计算app的开发，基本掌握了Android的开发原理，为以后的开发打下了坚实的基础。