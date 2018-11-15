#def get_info(uid):
uid = '16011008'
fname = uid+"assignment.txt"
f = open(fname, 'r',encoding='utf8')
subjects=[]

while True:
    line = f.readline()
        
    if not line:
        break
        
    if "공지사항" in line:
        try : 
            idx = line.index("→")
            lline = line[idx+1:]
            llist = lline.split(" ")
            string = llist[1][1:]
            if string in subjects:
                continue
            else:
                subjects.append(string)
            
        except: 
            print("!"+line)

#이거를 db에 저장해야됨.
print("subject list!")
print(subjects)
f.close()

#공지사항에 떴던 과목들 저장
fname = "subject.txt"
f = open(fname, 'w', encoding='UTF8')
try :
    for tr in subjects:
        print(tr)
        f.write(tr)
        f.write("\n")
except Exception as e: 
    print(e)
finally:
    print('finally')

f.close()
