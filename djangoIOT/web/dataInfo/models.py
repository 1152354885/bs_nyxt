from asyncio.windows_events import NULL
from distutils.command.upload import upload
from tkinter import CASCADE
from django.db import models

# Create your models here.
class dataInfo(models.Model):
    temperature=models.CharField(max_length=64,default=1)
    humidity=models.CharField(max_length=64,default=2)
    co2=models.CharField(max_length=64,default=3)
    light=models.CharField(max_length=64,default=4)
    time=models.CharField(blank=True,null=True,max_length=10)
    

class rfidInfo(models.Model):
    id=models.AutoField(primary_key=True)
    co2=models.CharField(max_length=64,default=3)
    time=models.CharField(max_length=64)
    

class adminInfo(models.Model):
    adminid=models.CharField(max_length=64,primary_key=True)
    adminpwd=models.CharField(max_length=64,default=123)
    adminuid=models.CharField(max_length=64,null=True, blank=True)
    adminname=models.CharField(max_length=64,null=True, blank=True)
    #adminphoto=models.CharField(max_length=255,null=True, blank=True,default=NULL)
    
