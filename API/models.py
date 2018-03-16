from django.db import models
import datetime

class User(models.Model):
   
    username  = models.CharField(max_length=100)
    email     = models.EmailField(max_length=100,unique=True,blank=False)
    gender    = models.CharField(max_length=10)
    password  = models.CharField(max_length=10) 
    img_url   = models.TextField(max_length=30)
    DateTimeCreated = models.DateTimeField(editable=False)
    isFBLogin  = models.BooleanField() 
    isLocationTracking = models.BooleanField()

    def __unicode__(self):
        return self.email

    def save(self, *args, **kwargs):
        ''' On save, update timestamps '''
        if not self.id:
             self.DateTimeCreated = datetime.datetime.today()
        return super(User, self).save(*args, **kwargs)

class preData(models.Model):

    user = models.ForeignKey(User)    
    latitude = models.CharField(max_length=20)
    longitude = models.CharField(max_length=20)
    remark = models.CharField(max_length=50)
    time = models.CharField(max_length=100)

class Data(models.Model):
    user = models.ForeignKey(User)
    start_lat = models.CharField(max_length=20)
    start_long = models.CharField(max_length=20)
    end_lat = models.CharField(max_length=20)
    end_long = models.CharField(max_length=20)
    time = models.CharField(max_length=100)

class Profile(models.Model):

    user = models.ForeignKey(User)
    phoneNo = models.CharField(max_length=10)
    carPlateNo = models.CharField(max_length=20)
    carModel = models.CharField(max_length=20)
    carMileage = models.CharField(max_length=20)
    homeAdd = models.CharField(max_length=50)
    officeAdd = models.CharField(max_length=50)

class Messages(models.Model):
    
    sender = models.CharField(max_length=20)
    receiver = models.CharField(max_length=20)
    content = models.TextField(max_length=100)
    DateTimeCreated = models.DateTimeField(editable=False)
    
    def save(self, *args, **kwargs):
        ''' On save, update timestamps '''
        if not self.id:
             self.DateTimeCreated = datetime.datetime.today()
        return super(Messages, self).save(*args, **kwargs)


    
    

    
 


