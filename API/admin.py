from django.contrib import admin
from API.models import User,preData,Profile,Messages,Data

class UserAdmin(admin.ModelAdmin):
      list_display = ('id', 'username', 'email', 'gender', 'isLocationTracking', 'isFBLogin')

class preDataAdmin(admin.ModelAdmin):
      list_display = ('id','user','latitude','longitude','time','remark')

class ProfileAdmin(admin.ModelAdmin):
      list_display = ('id', 'user', 'phoneNo', 'carPlateNo', 'carModel', 'carMileage', 'homeAdd', 'officeAdd')

class MessagesAdmin(admin.ModelAdmin):
      list_display = ('id', 'sender', 'receiver', 'content', 'DateTimeCreated')

class DataAdmin(admin.ModelAdmin):
      list_display = ('id','user','start_lat', 'start_long', 'end_lat', 'end_long', 'time')

admin.site.register(preData,preDataAdmin)
admin.site.register(User,UserAdmin)
admin.site.register(Profile,ProfileAdmin)
admin.site.register(Messages,MessagesAdmin)
admin.site.register(Data, DataAdmin)
