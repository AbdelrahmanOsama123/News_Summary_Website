from sklearn.cluster import OPTICS
import numpy as np
import os
dir = 'combine_news'
dir2 = 'combine_vectors'
for f in os.listdir(dir):
    os.remove(os.path.join(dir, f))
for f in os.listdir(dir2):
    os.remove(os.path.join(dir2, f))

data=[]
with open('average.txt', encoding= 'utf-8') as f:
   content = f.readlines()
temp = [x.strip() for x in content]

for i in range(len(temp)):
    temp2 =( temp[i].split())
    data.append(temp2)
    
vectors = [[float(v) for v in r[0].split(',')] for r in data]
arr = np.array(vectors)
clustering = OPTICS(min_samples=2).fit(arr)
clusters = []
clusters_temp = []
clusters_temp = clustering.labels_
if(min(clusters_temp) < 0):
    smallest = min(clusters_temp)
    for i in range (len(clusters_temp)):
        clusters.append(int(clusters_temp[i])-int(smallest))
else:
    clusters = clusters_temp
print(clusters)
for i in range(len(clusters)):
    if os.path.exists("combine_news\\{}.txt".format(clusters[i])):
        with open("news\\{}.txt".format(i), encoding= 'utf-8') as infile, open("combine_news\\{}.txt".format(clusters[i]),"a", encoding= 'utf-8') as outfile:
            for line in infile:
                if not line.strip(): continue  
                outfile.write(line)
        with open("vectors\\{}.txt".format(i), encoding= 'utf-8') as infile1, open("combine_vectors\\{}.txt".format(clusters[i]),"a", encoding= 'utf-8') as outfile1:
            for line in infile1:
                if not line.strip(): continue  
                outfile1.write(line)
    else:
        with open("news\\{}.txt".format(i), encoding= 'utf-8') as infile, open("combine_news\\{}.txt".format(clusters[i]),"w", encoding= 'utf-8') as outfile:
            for line in infile:
                if not line.strip(): continue  
                outfile.write(line)
        with open("vectors\\{}.txt".format(i), encoding= 'utf-8') as infile1, open("combine_vectors\\{}.txt".format(clusters[i]),"w", encoding= 'utf-8') as outfile1:
            for line in infile1:
                if not line.strip(): continue  
                outfile1.write(line)
        
check = open("check_files\\cluster_news.txt","w", encoding= 'utf-8')
check.close()
      

