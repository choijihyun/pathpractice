#def get_info(uid):
uid = '16011008'
fname = uid+"assignment.txt"
f = open(fname, 'r',encoding='utf8')
subject=[]

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
            if string in subject:
                continue
            else:
                subject.append(string)
            
            #print(llist)
        except: 
            print(line)

print(subject)
f.close()
