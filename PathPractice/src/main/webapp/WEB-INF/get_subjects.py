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


def get_info(uid, upw):
  #chrome의 경우, 아까 받은 chromedriver의 위치를 지정해준다.
    driver = webdriver.Chrome('/Users/USER/Downloads/chromedriver')

  #암묵적으로 웹 자원 로드를 위해 3초까지 기다림

    print("id"+uid)
    print("pw"+upw)
    
    stuid =str(uid)
    pw = str(upw)

  #url접근
    
    driver.get('https://blackboard.sejong.ac.kr')
    driver.implicitly_wait(3)
    driver.find_element_by_id('toggle_login_form').click()
    
    driver.find_element_by_name('user_id').send_keys(stuid)
    try:
        alert = browser.switch_to_alert()
        alert.accept()
        print ("alert accepted")
    except:
        print ("no alert")

    print("후엥")
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


  #ipython 써서 확인하기
    sleep(10)
    fname = stuid+"subject.txt"
    f = open(fname, 'w', encoding='UTF8')

    text = driver.page_source
    soup = BeautifulSoup(text,'html.parser')
    rd=soup.find("div","coursefakeclass")

    try :
        for tr in rd:
            print(tr)
            f.write(tr.text)
            f.write("\n")
    except Exception as e: 
        print(e)
    finally:
        print('finally')

    f.close()
    driver.quit()

f = open('userInfo.txt','r', encoding='UTF8')

a=[]
while True:
    line = f.readline()
    if not line: break
    a.append(line)

stuid =a[0].rstrip("\r\n")
pw =a[1]
print(stuid, pw)
get_info(stuid, pw)

f.close()

