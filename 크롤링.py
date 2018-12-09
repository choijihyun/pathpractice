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


#datetime 얻어내는 함수
def get_time(line):
    var=0 #일수 기준
    if " 전" in line:
        if "시간" in line:
            var = 0
        elif "일" in line:
            idx = line.index("전")
            time = line[:idx-2]
            var = int(re.findall('\d+', time)[0])

        mm=(datetime.today().month)
        
        dd=datetime.today().day - var
        if dd <= 0:
            if mm == 1:
                mm = 12
            else :
                mm-=1

            if mm==0:
                mm=12
            if mm==3 or mm==5 or mm==7 or mm==8 or mm==10 or mm==12:
                dd = 31+dd
            elif mm==4 or mm==6 or mm==9 or mm==11:
                dd = 30+dd

        dd = str(dd)
        if len(dd)==1:
            dd="0"+dd
        mm = str(mm)
        if len(mm)==1:
            mm="0"+mm
        return (str(datetime.today().year)+"-"+mm+"-"+dd)



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
                break
            line = tr.text
            lines = line.split("\n")
            for line in lines:
                #print(lines[0])
                if "열기" in line:
                    continue
                if "닫기" in line:
                    continue
                #if "오" in line:
                #    continue
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
                    if "시스템" in line:
                        subjects.append("시스템 공지사항")
                        continue
                    idx = line.index("→")
                    lline = line[idx+3:]
                    try:
                        idx2 = lline.index("(")
                    except:
                        print("인덱스 못가져옴")
                    string = lline[:idx2-1]
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
                    idx2 = lline.index("(")
                except:
                    print("인덱스 못가져옴")
                string = lline[:idx2-1]
                if string in subjects:
                    continue
                else:
                    subjects.append(string)
                
                

    return (subjects)

# 시간 과목 내용 파싱
def parsing(announcement,subjects):
    flag=0
    nalzza ="0"
    subject_name = "0"
    context = "0"
    PARSE=[]
    parse=[]
    for line in announcement:
        print(line)
        
        flag=0
        parse=[]
        context=""
        subject_name=""
        for j in line:
            if eq(j,line[0]): #처음줄은 무조건 날짜나옴
                nalzza = get_time(j)
            else:
                if "공지사항" in j:
                    for subject in subjects:
                        if subject in j:
                            subject_name = subject
                    if ")" in j:
                        idx = j.index(")")
                        context = j[idx+1:] # 내용 저장
                        context += "\n"
                elif "코스·조직" in j:
                    for subject in subjects:
                        if subject in j:
                            subject_name = subject

                else :
                    if "내용" in j:
                        j.replace("내용","")
                    context += j
                    context+="\n"
                    
        parse.append(nalzza)
        parse.append(subject_name)
        parse.append(context)
        PARSE.append(parse)

    return (PARSE)

#blackboard 스크롤링 함수
def check_user(uid, upw):
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
        

#####################################################################

#MySQL Connection 연결
conn = pymysql.connect(host='203.250.148.53',
                       port=3306,
                       user='jihyun',
                       passwd='root',
                       db='PATH')

#connectino으로 부터 cursor 생성
curs = conn.cursor()

sql = "select stuId, pw from Student"
curs.execute(sql)
rows = curs.fetchall()

for stu in rows:
    lists=[]
    content=[]
    subjects=[]
    #lists=check_user(stu[0], stu[1])
    #if lists == [] :
    #    sql = "UPDATE Student SET flag=0 WHERE stuId ="+stu[0]
    #    curs.execute(sql)
    #    conn.commit()
    #    continue

    lists=get_info(stu[0],stu[1])
    if lists==[]:
        try :
            conn.close()
        except Exception as e: 
            print(e)
        finally:
            print('finally')
        print("다시 위로")
        continue


    conn = pymysql.connect(host='203.250.148.53',
                       port=3306,
                       user='jihyun',
                       passwd='root',
                       db='PATH')
    curs = conn.cursor()
    
    #과목 불러와서 subjects list에 저장함
    subjects = get_subject(lists)

    #parsing한 내용 content list에 저장
    content = parsing(lists, subjects)

    for subject in subjects:
        print(subject)
    sql="UPDATE Student SET flag=1 where stuId="+stu[0]
    curs.execute(sql)
    conn.commit()
    
    for i in content:
        if eq(i,content[0]):
            continue
        if "'" in i[2]:
            contest = '"'+i[2]+'"'
        else :
            contest = "'"+i[2]+"'"
        date = "'"+i[0]+"'"
        subject = "'"+i[1]+"'"
        sql = "SELECT count(*) FROM `Announcement` WHERE contest like "+ contest+" and stuId = "+stu[0]
        curs.execute(sql)
        row = curs.fetchall()
        row = str(row)
        i = int(re.findall('\d+', row)[0])
        if i > 0:
            continue
        sql="insert into Announcement(stuId,date,subject,contest) values ("+stu[0]+","+date+","+subject+","+contest+")"
        curs.execute(sql)
        conn.commit()
        
        
    try :
        conn.close()
    except Exception as e: 
        print(e)
    finally:
        print('finally')
try :
    conn.close()
except Exception as e: 
    print(e)
finally:
    print('finally')
    
