import os
import string
import random
import sys


print ('Argument List:', str(sys.argv))
print ('Creating ', str(sys.argv[1]), 'file')



def generateString():
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(5))

def generateEmail():
    return generateString()+"@"+generateString()+".com"

fileName = str(sys.argv[1])+".csv"

bytes = 0
if str(sys.argv[1])=="small":
    bytes = 2097152;
elif str(sys.argv[1])=="medium":
    bytes = 524288000;
elif str(sys.argv[1])=="large":
    bytes = 2147483648;

f = open(fileName, "a")

size = os.stat(fileName).st_size
while size < bytes:
    f.write("\""+generateEmail()+"\";\""+generateString()+"\";\""+generateString()+"\"\n")
    size = os.stat(fileName).st_size

f.close
