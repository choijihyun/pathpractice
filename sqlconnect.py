import re
#문자열 같은지 비교
from operator import eq
#현재 시간 가져오는 모듈
from datetime import datetime
#db랑 python 연결
import pymysql



#MySQL Connection 연결
conn = pymysql.connect(host='203.250.148.53',
                       port=3306,
                       user='jihyun',
                       passwd='root',
                       db='PATH')
try:
    #connectino으로 부터 cursor 생성
    curs = conn.cursor()

    #sql문 실행
    sql = "insert into Announcement values (\"16011008\",\"2018-11-20\",\"test\",\"test\")"
    curs.execute(sql)
    conn.commit()
    sql = "select * from Announcement"
    curs.execute(sql)
    rows = curs.fetchall()
    for row in rows:
        print(row)
except Exception as e:
   ("Exeception occured:{}".format(e))

finally:
    sql = "select * from Announcement"
    curs.execute(sql)
    rows = curs.fetchall()
    for row in rows:
        print(row)
    curs.close()

        
