# gecco_demo <br>
主要是使用gecco框架<br>
运用gecco框架抓取京东，天猫，一号店的对应的数据然后存储到数据库中<br>
jdbc.properties为数据库连接数据<br>
init.sql为数据库建表sql<br>
<br>
启动server,server监听10000端口<br>
向端口发送url type 其中url为对应的数据地址，地址为http://list.yhd.com/{category},https://list.tmall.com/search_product.htm?{text},https://list.jd.com/list.html?cat={id}
三种格式的地址,type为这一地址下的数据位什么类型<br>
