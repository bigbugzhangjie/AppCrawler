import Xlib.display as ds
import Xlib.X as X
import Xlib.ext.xtest as xtest

class mouse():
    '''mouse class which contains couple of mouse methods'''
    def __init__(self):
        self.display = ds.Display()

    def mouse_press(self,button):
        '''button= 1 left, 2 middle, 3 right, 4 middle up, 5 middle down'''
        xtest.fake_input(self.display,X.ButtonPress, button)
        self.display.sync()
    def mouse_release(self,button):
        '''button= 1 left, 2 middle, 3 right, 4 middle up, 5 middle down'''
        xtest.fake_input(self.display,X.ButtonRelease, button)
        self.display.sync()
    def mouse_click(self,button):
        '''button= 1 left, 2 middle, 3 right, 4 middle up, 5 middle down'''
        self.mouse_press(button)
        self.mouse_release(button)

    def goto_xy(self,x, y):
        '''move to position x y'''
        xtest.fake_input(self.display, X.MotionNotify, x = x, y = y)
        self.display.flush()

    def pos(self):
        '''get mouse position'''
        coord = self.display.screen().root.query_pointer()._data
        return (coord["root_x"],coord["root_y"])
   
    def screen_size(self):
        '''get screen size'''
        width = self.display.screen().width_in_pixels
        height = self.display.screen().height_in_pixels
        return (width,height)

i=0
def elapse():
    '''get elapse time, gap is 0.1 second'''
    global i
    i+=1	#i+=0.1
    return i

if __name__ == "__main__":
   
    import sys
    import time as tm
   
    t=0
    m=mouse()
    mouse_ev=""
    ev=[]
    m=mouse()
    EVDIC={ "press":m.mouse_press,
            "release":m.mouse_release,
            "click":m.mouse_click,
            "sleep":tm.sleep }

    if sys.argv[1] == "-r":
        try:
            rctm=sys.argv[2]
        except:
            sys.exit(1)
        try:
            logfile=sys.argv[3]
        except:
            logfile="./mouse.log"

        f=open(logfile,"w")
        while t <= float(rctm):
            t=elapse()
            ps=m.pos()
            f.write(str(ps[0])+","+str(ps[1])+"\n")
            tm.sleep(1) #            tm.sleep(0.1)
        f.close()
    elif sys.argv[1] == "-p":
        try:
            f=open(sys.argv[2])
        except:
            sys.exit(2)

        coord=f.readlines()
        f.close()

        for pos_xy in coord:
            try:
                pos_x,pos_y=pos_xy.replace("\n","").split(",")
            except ValueError:
                pos_x,pos_y,mouse_ev=pos_xy.replace("\n","").split(",")
                ev=mouse_ev.split(":")

            m.goto_xy(int(pos_x),int(pos_y))
           
            if ev and ev[1]:
                if ev[0] != "sleep":
                    EVDIC[ev[0]](int(ev[1]))
                else:
                    EVDIC[ev[0]](float(ev[1]))
                ev=[]

            tm.sleep(0.1)

