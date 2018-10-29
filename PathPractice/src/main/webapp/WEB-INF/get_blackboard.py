from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from time import sleep
from selenium.webdriver.common.keys import Keys


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
    sleep(2)
    alert = driver.switch_to_alert()
    alert.accept()
    driver.find_element_by_name('password').send_keys(pw)

    driver.implicitly_wait(3)
    alert = driver.switch_to_alert()
    alert.accept()
    driver.find_element_by_id("entry-login").click()

    alert = driver.switch_to_alert()
    alert.accept()

  #공지사항 찾아가기
    driver.get('https://blackboard.sejong.ac.kr/webapps/streamViewer/streamViewer?cmd=view&streamName=alerts&globalNavigation=false')
    driver.implicitly_wait(5)



  #ipython 써서 확인하기
    sleep(10)
    fname = stuid+"assignment.txt"
    f = open(fname, 'w', encoding='UTF8')

    text = driver.page_source
    soup = BeautifulSoup(text,'html.parser')
    rd=soup.select(".stream_item")

    try :
        for tr in rd:
            print(tr.text)
            f.write(tr.text)
    except Exception as e: 
        print(e)
    finally:
        print('finally')

    f.close()
    driver.quit()
      

        

    

