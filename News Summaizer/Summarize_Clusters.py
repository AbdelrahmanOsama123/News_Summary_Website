from sklearn.cluster import OPTICS
import numpy as np
import os
if os.path.exists("summary.txt"):
  os.remove("summary.txt")
if os.path.exists("dr_clusters.txt"):
  os.remove("dr_clusters.txt")
file_for_dr = open ('dr_clusters.txt', 'a', encoding= 'utf-8')
count = 1
for filename in os.listdir("combine_vectors"):
    sentences_list = [] #delete it
    final_cluster = []
    data = []
    with open('combine_vectors\\{}'.format(filename), encoding= 'utf-8') as f:
       content = f.readlines()
    temp = [x.strip() for x in content]
    
    with open('combine_news\\{}'.format(filename), encoding= 'utf-8') as f2:
       content2 = f2.readlines()
    sentences_list = [x.strip() for x in content2]

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
    final_cluster = [[] for i in range(len(set(clusters)))]
     
    for i in range(len(sentences_list)):
        final_cluster[clusters[i]].append(sentences_list[i])

    file_for_dr.write('News Cluster #' + str(count) + ' : \n')
    count=count+1
    for i in range (len(final_cluster)):
        file_for_dr.write('Cluster #' + str(i+1) + ' : \n')
        for j in range (len(final_cluster[i])):
           file_for_dr.write(str(final_cluster[i][j] + '\n'))
        file_for_dr.write('\n')
        res = max(final_cluster[i], key = len)
        file3 = open('summary.txt', 'a', encoding= 'utf-8') 
        file3.write(res + "\n")
        file3.close()
file_for_dr.close()
check = open("check_files\\finish.txt", "w", encoding= 'utf-8')
check.close()




