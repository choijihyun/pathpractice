# -*- coding: utf-8 -*-
# UTF-8 encoding when using korean
from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from time import sleep
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.alert import Alert
#db랑 python 연결
import re
#문자열 같은지 비교
from operator import eq
#현재 시간 가져오는 모듈
from datetime import datetime
#db랑 python 연결
import pymysql
import sys

#blackboard 스크롤링 함수
def get_info(uid, upw):
  #chrome의 경우, 아까 받은 chromedriver의 위치를 지정해준다.
    driver = webdriver.Chrome('/Users/USER/Downloads/chromedriver')

  #암묵적으로 웹 자원 로드를 위해 3초까지 기다림
    
    stuid =str(uid)
    pw = str(upw)

  #url접근
    
    driver.get('https://blackboard.sejong.ac.kr')
    driver.implicitly_wait(3)
    driver.find_element_by_id('toggle_login_form').click()

    try:
        driver.find_element_by_name('user_id').send_keys(stuid)
        try:
            alert = browser.switch_to_alert()
            alert.accept()
            print ("alert accepted")
        except: 
            print ("no alert")

    
        driver.find_element_by_name('password').send_keys(pw)
        try:
            alert = browser.switch_to_alert()
            alert.accept()
            print ("alert accepted")
        except:
            print ("no alert")

        driver.implicitly_wait(3)
        driver.find_element_by_id("entry-login").click()
        try:
            alert = browser.switch_to_alert()
            alert.accept()
            driver.find_element_by_id("entry-login").click()
            print ("alert accepted")
        except:
            print ("no alert")
    except:
        print("블랙보드 유저가 아님")
        driver.quit()
        return False
        

  #공지사항 찾아가기
    driver.get('https://blackboard.sejong.ac.kr/webapps/streamViewer/streamViewer?cmd=view&streamName=alerts&globalNavigation=false')
    driver.implicitly_wait(5)


    #ipython으로 한단계 한단계 확인할 수 있음
    sleep(10)

    text = driver.page_source
    soup = BeautifulSoup(text,'html.parser')
    rd=soup.select(".stream_item")
    
    flag=0
    announcements=[]
    content=[]
    try :
        for tr in rd:
            if flag==1:
                break;
            line = tr.text
            lines = line.split("\n")
            for line in lines:
                if "열기" in line:
                    continue
                if "닫기" in line:
                    continue
                if "오" in line:
                    continue
                if " 전" in line and eq(line,lines[0]):
                    idx = line.index("전")
                    if "일" in line:
                        time = line[:idx-2]
                        i = int(re.findall('\d+', time)[0])
                        if i>20:
                            flag=1
                    announcements.append(content)
                    content=[]
                    lline = line[idx+1:]
                    day = line[:idx+1]
                    content.append(day)
                    content.append(lline)
                else:
                    content.append(line)
            
    except Exception as e: 
        print(e)
    finally:
        print('finally')

    driver.quit()
    return (announcements)


# 시간 내용 과목 리스트 형식으로 나뉜거 받아서 파싱하는 함수
def get_subject(lists):
    subjects=[]
    for ll in lists:
        for line in ll:
            if "공지사항" in line:
                try :
                    idx = line.index("→")
                    lline = line[idx+3:]
                    try:
                        idx2 = lline.index(")")
                    except:
                        print("인덱스 못가져옴")
                    string = lline[:idx2+1]
                    if string in subjects:
                        continue
                    else:
                        subjects.append(string)
                except: 
                    print("!"+line)

            if "코스·조직" in line:
                idx = line.index(":")
                lline = line[idx+2:]
                try:
                    idx2 = lline.index(")")
                except:
                    print("인덱스 못가져옴")
                string = lline[:idx2+1]
                if string in subjects:
                    continue
                else:
                    subjects.append(string)
                
                

    return (subjects)


## 듣는 수업 및 분반 알아내기
lists = get_info(16011008,'sechljigusjong98')
subjects = get_subject(lists)
INFO=[]
INFOS=[]
for subject in subjects:
    INFO=[]
    info = subject.split("(")
    #print(info)
    name = info[0]
    classnum = info[1]
    idx = classnum.index(")")
    classnum = classnum[:idx]
    print("name"+name)
    print("classnum"+classnum)
    INFO.append(name)
    INFO.append(classnum)
    INFOS.append(INFO)

#MySQL Connection 연결
conn = pymysql.connect(host='203.250.148.53',
                       port=3306,
                       user='jihyun',
                       passwd='root',
                       db='PATH')
#connectino으로 부터 cursor 생성
curs = conn.cursor()

sql = "select stuId from Student"
curs.execute(sql)
rows = curs.fetchall()

for stu in rows :
    
    ## 수업 subkey찾아서 timtiable DB에 저장해야 함 
    for info in INFOS:
        try :
            className = info[0]
            classNum = info[1]
            sql = "select subjectKey from Subject where subName = '"+className+"' and classNum = '"+classNum+"'"
            curs.execute(sql)
            rows = curs.fetchall()
            subKey = str(rows[0][0])

            stuId = stu[0]
            sql = "select count(*) from TimeTable where stuId = '"+stuId+"' and subjectKey = "+subKey
            curs.execute(sql)
            row = curs.fetchall()
            row = str(row)
            i = int(re.findall('\d+', row)[0])
            if i>0 :
                print("이미 존재함 ")
                continue
            sql = "INSERT INTO TimeTable (stuId, subjectKey) VALUES ('"+stuId+"',"+subKey+")"
            curs.execute(sql)
            conn.commit()
            rows = curs.fetchall()
            print("성공!")
        except :
            print("except")

