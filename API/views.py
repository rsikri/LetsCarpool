from django.shortcuts import render
from API.models import User,preData,Profile,Messages
import json
from django.contrib.auth.hashers import make_password,check_password
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.db.models import Q

response = {}

@csrf_exempt
def registration(request):
    if request.method=='POST':
       try:
         user = User.objects.get(email = request.POST["email"])
       except:
         user = User.objects.create(username=request.POST["username"], email=request.POST["email"], password = request.POST["password"],
 					isLocationTracking=True, isFBLogin = False)
         user.save()
         return HttpResponse(status=201)
       # If user already exist do not register again    
       return HttpResponse(status = 409)

@csrf_exempt
def fb_registration(request):
    if request.method=='POST':
       try:
         user = User.objects.get(email = request.POST["email"])
       except:
         user = User.objects.create(username=request.POST["username"], email=request.POST["email"], gender=request.POST["gender"], 
                                    img_url=request.POST["picture_url"], isLocationTracking=True, isFBLogin = True)
         user.save()
         return HttpResponse(status=201)
       # If user already exist do not register again	
       return HttpResponse(status = 409)

@csrf_exempt
def login(request):
    if request.method=='POST':
       try:
         user = User.objects.get(username = request.POST["username"])
       except:
         return HttpResponse(status=400)

       if request.POST["password"] == user.password :
          return HttpResponse(status = 200)
       else:
          return HttpResponse(status = 400)

@csrf_exempt
def push_data(request):
    if request.method=='POST':
       data = json.loads(request.POST["data"])
       for d in data:
           try:
             user = User.objects.get(email=d["user"])
           except:  
             return HttpResponse(status=400)

           data = preData.objects.create(user=user,time=d["time"],latitude=d["latitude"],longitude=d["longitude"],remark=d["remark"])
           data.save()
       
       return HttpResponse(status = 200)

@csrf_exempt
def getCarPoolers(request):
    if request.method == 'POST' :
       poolers = []
       pooler = {}
       if bool(request.POST) != False :
          data = json.loads(request.POST['friendsList'])
          for d in data:
              try:
                user = User.objects.get(username=d['name'].encode('ascii', 'ignore'))
              except:
                print "User does not exists"
              pooler = {'name':user.username.encode('ascii', 'ignore'), 'gender':user.gender.encode('ascii', 'ignore'), 
			'img':user.img_url.encode('ascii', 'ignore')}
              poolers.append(pooler)       
       for user in User.objects.all():
           if user.isFBLogin == False :
              pooler = {'name': user.username, 'gender': user.gender}
              poolers.append(pooler)    
       return HttpResponse(json.dumps({"data":poolers}))

@csrf_exempt
def save_profile(request):
    if request.method == 'POST':
       try:
         user = User.objects.get(username=request.POST['username'])
       except:
         return HttpResponse(status=400)
       try:
         profile = Profile.objects.get(user=user)
       except:
         profile = Profile.objects.create(user=user, phoneNo = request.POST['phone'], carPlateNo = request.POST['plate_no'], 
					carModel = request.POST['model'], homeAdd = request.POST['home_add'], 
                                        carMileage = request.POST['mileage'], officeAdd = request.POST['office_add'])
         profile.save()
         return HttpResponse(status=200)
       profile.phoneNo = request.POST['phone']
       profile.carPlateNo = request.POST['plate_no']
       profile.carModel = request.POST['model']
       profile.homeAdd = request.POST['home_add']
       profile.officeAdd = request.POST['office_add']
       profile.carMileage = request.POST['mileage']
       profile.save()
       return HttpResponse(status=200)
   
    if request.method == 'GET':
	try:
          profile = Profile.objects.get(user__username = request.GET['username'])
        except:
          return HttpResponse(status=404)
        profile = {"username": profile.user.username,"gender":profile.user.gender,"phoneNo":profile.phoneNo,"carPlateNo":profile.carPlateNo, 
                   "carModel":profile.carModel,"carMileage":profile.carMileage,"homeAdd":profile.homeAdd,"officeAdd":profile.officeAdd}
        return HttpResponse(json.dumps({"data":profile}))

@csrf_exempt
def send_message(request):
    if request.method == 'POST':
       message = Messages.objects.create(sender = request.POST['sender'], receiver = request.POST['receiver'], content = request.POST['content'])
       message.save()
       return HttpResponse(status=200)

@csrf_exempt
def fetch_message(request):
    if request.method == 'POST':
       messagesList = []
       message = {}
       messages = Messages.objects.filter(Q(sender=request.POST['username']) | Q(receiver=request.POST['username']))
       for message in messages:
           m = {"content" : message.content }
           messagesList.append(m)
       return HttpResponse(json.dumps({'data':messagesList}))
