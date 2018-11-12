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
  driver.implicitly_wait(3)
    print("id"+uid)
  uid = '16011008'
  upw = 'sechljigusjong98'

  #url접근
  driver.get('https://blackboard.sejong.ac.kr')
  driver.find_element_by_id('toggle_login_form').click()

  driver.find_element_by_name('user_id').send_keys(uid)
  driver.find_element_by_name('password').send_keys(upw)


  driver.find_element_by_id("entry-login").click()

  #공지사항 찾아가기
  driver.get('https://blackboard.sejong.ac.kr/webapps/streamViewer/streamViewer?cmd=view&streamName=alerts&globalNavigation=false')
  driver.implicitly_wait(5)



  #ipython 써서 확인하기
  sleep(10)
  fname = '"'+uid+"+assignment.txt"
  f = open('fname, 'w', encoding='UTF8')

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
      

        

    

