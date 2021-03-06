# 指令说明    https://www.cnblogs.com/turnip/p/13786970.html
    ^XA //条码打印指令开始
    ^MD30 //设置色带颜色的深度（浓度）, 取值范围从-30到30
    ^LH60,10 //设置条码纸的边距
    ^FO20,10 //设置条码左上角的位置
    ^ACN,18,10 //设置字体
    ^BY1.4,3,50 //设置条码样式。1.4是条码的缩放级别，3是条码中粗细柱的比例, 50是条码高度
    ^BC,,Y,N //打印code128的指令
    ^FD12345678^FS //设置要打印的内容, ^FD是要打印的条码内容^FS表示换行
    ^XZ //条码打印指令结束

^XA 
^JMA
    
    每毫米设定点，可选参数【A,B】，
    A表示【24 dots/mm, 12 dots/mm, 8 dots/mm or 6 dots/mm】，
    B表示【12 dots/mm, 6 dots/mm, 4 dots/mm or 3 dots/mm】，
    默认A

^LL200 

    标签高度，这里是20mm
^PW680

    标签宽度，这里是68mm
^MD10

    标签深度，可选值【-30~30】，值越高标签浓度越高
^PR2
    
    打印速度，实际这是一个多参数的指令，^PRa,b,c，b和c不设置则为默认值。其中a是打印速度，可选值【1-14，A-E】，值越大速度越快，
    其中字母【2=A,3=B,4=C,6=D,8=E】,即设置A与设置2无异。b和c的参数用于设置推出和回卷速度，默认即可。
^PON

    打印方向，有【N，I】两个值可选，N是正常，I是倒置（标签底部先出）
^LRN

    打印反转，有【Y,N】两个值可选，N是正常，Y表示将产生黑底白字（需要先绘制黑色填充方框）
^LH0,0

    标签起点
^FO10,30  

    打印坐标FOx,y

^A0N,72,72 

    字体参数 ^Aab,c,d
    ^A有a,b,c,d四个参数
    a，字体类型，的取值范围从【0-9，A-Z】，0是默认的内置字体，若需要其他字体则需要设置，在打印中文这一节将会说明。
    b，旋转角度，注意参数a和b之间并没有逗号，有【N,R,I,B】四个选项，分别代表正常，顺时针旋转90°，180°、270°。
    c，字符高度
    d，字符宽度

^AZN,72,72

    注意第一个参数Z，它代表使用我们自己设置的Z字体，72代表字体的大小，由于使用了点阵字体，这里的大小必须是24的整数倍。
^SEE:GB18030.DAT^FS

    该指令共有三个参数
    ^SEa:b.c
    参数a代表本地编码表的选择，可选值有【R,E,B,A】这四个参数的值定义暂时没有详细的资料，目前知道它用于区分ZPL指令和ZPL II指令，默认为R，这里使用E。
    参数b代表编码的名称，目前网上找到的资料都是使用GB18030，暂时没有发现其他编码。
    参数c代表编码的后缀名，一般是DAT。

^CWZ,E:SIMSUN.FNT

    该指令共有4个参数
    ^CWa,b:c.d
    参数a代表设置的这个字体编号，可选值【A-Z和0-9】，当这里设置了以后，^A的第一个参数才能引用到这个字体。
    参数b同^SE的参数a。
    参数c代表字体名称，这里的SIMSUN是宋体，可参考
    https://blog.csdn.net/amy_king_0/article/details/54141460
    尝试设置更多字体类型，但是打印出来没啥区别。
    参数d代表字体后缀名，一般是FNT。
    在使用python发送打印指令时，由于使用读取txt的方式发送打印指令，需要检查文本文档编码是否为UTF-8，方法是点击文件-另存为时查看编码

打印带中文二维码

    ^XA
    ^MMT
    ^PW1000
    ^LL100
    ^FT100,5
    ^BY3,1,50
    ^BCN,50,Y,N,N
    ^FD02356^FS
    ^XZ

带中文例子

    ^XA
    ^SEE:GB18030.DAT^FS
    ^CWZ,E:SIMSUN.FNT
    ^CI26
    ^JMA^LL200^PW680^MD10^PR2^PON^LRN^LH0,0
    ^FO20,0
    ^AZN,72,72
    ^FD中123文ABC测试^FS
    ^PQ1
    ^XZ

    ^XA
    ^SEE:GB18030.DAT^FS
    ^CWZ,E:SIMSUN.FNT
    ^JMA^LL800^PW700^MD0^PR3^PON^LRN^LH0,0
    ^CI26
    ^FO180,50^A0N,130,130
    ^FD1234^FS
    ^FO600,50^AZN,96,96
    ^FD补^FS
    ^FO600,50^GB95,95,1^FS
    ^FO0,200^A0N,80,80
    ^FD20001234^FS
    ^FO500,220^A0N,70,70
    ^FDAB123^FS
    ^FO0,300^A0N,80,80
    ^FD20171234ABCD^FS
    ^FO550,300^A0N,70,70
    ^FDNo.1^FS
    ^FO0,450^AZN,48,48
    ^FD毛重：478.03 g^FS
    ^FO0,500^AZN,48,48
    ^FD皮重：178.03 g^FS
    ^FO0,550^AZN,48,48
    ^FD净重：300.00 g^FS
    ^FO380,400
    ^BQN,2,6
    ^FDHM,B0200 1234#20001234#AB123#20171234ABCD#No.1#pi478.03g#mao178.03g#jing300.00g^FS
    ^PQ1
    ^XZ
