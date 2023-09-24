import tensorflow_hub as hub
import tensorflow as tf
import tensorflow_text
import os
embed = hub.load("https://tfhub.dev/google/universal-sentence-encoder-multilingual/3")
if os.path.exists("average.txt"):
  os.remove("average.txt")
dir = 'vectors'
for f in os.listdir(dir):
    os.remove(os.path.join(dir, f))
counter = 0

for filename in os.listdir("news"):    
    with open("news\\{}".format(filename), encoding= 'utf-8') as f:
        content = f.readlines()
    sen = [x.strip() for x in content]
    dim = 40
    f = open("vectors\\{}.txt".format(counter),"w", encoding= 'utf-8')
    for j in range(len(sen)):
        for i in range(dim):
            if(i != dim-1):
                f.write(str(tf.keras.backend.get_value(embed(sen))[j][i])+",")
            else:
                f.write(str(tf.keras.backend.get_value(embed(sen))[j][i]))
        f.write("\n")
    print(counter)
    counter = counter + 1 
    f.close() 
for filename in os.listdir("vectors"):
    data = []
    with open("vectors\\{}".format(filename), encoding= 'utf-8') as f:
        content = f.readlines()
    temp = [x.strip() for x in content]

    for i in range(len(temp)):
        temp2 =( temp[i].split())
        data.append(temp2)
    vectors = [[float(v) for v in r[0].split(',')] for r in data]
    avg = [float(sum(col))/len(col) for col in zip(*vectors)]
    file3 = open('average.txt', 'a', encoding= 'utf-8')
    for i in range(len(avg)):
        if(i != (len(avg)-1)):
            file3.write(str(avg[i])+ ",")
        else:
            file3.write(str(avg[i]))
    file3.write("\n")
    file3.close()
check = open("check_files\\vectorization.txt","w", encoding= 'utf-8')
check.close()








    
