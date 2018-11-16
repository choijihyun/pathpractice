import re

#def get_info(uid):

uid = '16011008'
fname = uid+"assignment.txt"
f = open(fname, 'r',encoding='utf8')
announcements=[]
content=[]
while True:
    line = f.readline()
        
    if not line:
        break
        
    if " 전" in line:
        announcements.append(content)
        content=[]
        idx = line.index("전")
        time = line[:idx-2]
        i = int(re.findall('\d+', time)[0])
        if i>20:
            break
        lline = line[idx+1:]
        day = line[:idx+1]
        content.append(day)
        content.append(lline)
  
    else:
        if "열기" in line:
            continue
        if "닫기" in line:
            continue
        content.append(line)

f.close()

fname="parsing.txt"
f=open(fname,"w",encoding='utf8')
for i in announcements:
    for j in i:
        f.write(j)
        f.write("\n")
    f.write("\n")
    

f.close()
