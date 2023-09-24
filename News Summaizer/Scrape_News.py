from newspaper import Article
import os
from requests.exceptions import ConnectionError
dir = 'news'
for f in os.listdir(dir):
    os.remove(os.path.join(dir, f))
file1 = open('links.txt', 'r', encoding= 'utf-8')
Lines = file1.readlines()
counter = 0
counter2 = 100
def scrape(url):
        article = Article(url, language="ar")
        article.download()
        article.parse()
        texttemp =" "
        if(article.text[0:3] == "كتب" or article.text[0:6] == "الكاتب" or article.text[0:5] == "كتابة"):
            texttemp = "\n".join(article.text.split("\n")[1:])
        else:
            texttemp = article.text
            texttemp = texttemp.replace("لمزيد من التفاصيل", "")
            texttemp = texttemp.replace("اضغط هنا", "")
            texttemp = texttemp.replace("إقرأ المزيد","")
            texttemp = texttemp.replace("..", ".")
            texttemp = texttemp.replace(".", ".\n")
            texttemp = texttemp.replace("\n\n\n", "\n")
            texttemp = texttemp.replace("\n\n", "\n")
        #print(texttemp)
        #print(article.title)
        #print(article.top_image)
        file2 = open('news\\{}.txt'.format(counter2), 'w', encoding= 'utf-8')
        file2.write(texttemp)
        file2.close()
        with open('news\\{}.txt'.format(counter2), encoding= 'utf-8') as infile, open('news\\{}.txt'.format(counter), 'w', encoding= 'utf-8') as outfile:
            for line in infile:
                if not line.strip() or len(line) < 100: continue  # skip the empty line
                outfile.write(line) 
        os.remove('news\\{}.txt'.format(counter2))
            
for line in Lines:
        try:
            scrape(line.strip())
            counter = counter+1
            counter2 = counter2+1
        except:
            file3 = open('black_list.txt', 'a', encoding= 'utf-8') 
            file3.write(line[0:line.find('.',15)] + "\n")
            file3.close()
            continue

f = open("black_list.txt","r", encoding= 'utf-8')
fl = f.readlines()
f.close()
flset = list(set(fl))
f = open("black_list.txt","w", encoding= 'utf-8')
for line in flset:
    f.write(line)
f.close()
check = open("check_files\\scrape.txt","w", encoding= 'utf-8')
check.close()


