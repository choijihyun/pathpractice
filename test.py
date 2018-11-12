import pymysql

#MySQL Connection 연결
conn = pymysql.connect(host='203.250.148.53',
                       port='8090',
                       user='jihyun',
                       passwd='root',
                       db='PATH')

#connectino으로 부터 cursor 생성
curs = conn.cursor()

#sql문 실행
sql = "select * from Student"
curs.execute(sql)

#data fetch
rows = curs.fetchall()
print(rows)

conn.close()
