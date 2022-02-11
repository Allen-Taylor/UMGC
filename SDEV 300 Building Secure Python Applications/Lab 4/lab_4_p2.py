import hashlib 
# input a message to encode
print('Enter a message to encode:')
message = input() 
# encode it to bytes using UTF-8 encoding
message = message.encode() 
# hash with MD5 (very weak)
print(hashlib.md5(message).hexdigest()) 
4
# Lets try a stronger SHA-2 family
print(hashlib.sha256(message).hexdigest()) 
print(hashlib.sha512(message).hexdigest())