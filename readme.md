## ViewPager子View滑动事件冲突解决
- 事件分发：public boolean dispatchTouchEvent(MotionEvent ev)

分发事件逐层向下传递。事件首先由dispatchTouchEvent方法分发，分发逻辑如下：

```
    return true;由该dispatchTouchEvent方法消费并且停止分发。

    return false;返回给子view的dispatchTouchEvent方法处理

    return super.dispatchTouchEvent(ev);由当前view的onInterceptTouchEvent（）拦截处理。
```

 - 事件拦截：public boolean onInterceptTouchEvent(MotionEvent ev)

```
当前view拦截到事件后，处理流程如下：

    return false；表示放行由当前view的子view的dispatchTouchEvent分发处理。

    return true或return super.onInterceptTouchEvent()表示拦截该事件，由该View的Ontouch方法处理。
```

- 事件响应：public boolean onTouchEvent(MotionEvent ev)

```
    return true表示消费。

   return false或return super.onTouch()则此事件传递给上层View的onTouch方法处理。
```

***

#### 当ViewPager中有图片轮播时,会产生冲突,子View无法获取触摸事件

- 自定义ViewFillper重写分发事件

```
@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
```
> getParent()返回的是ViewParent,即子View的父控件
> requestDisallowInterceptTouchEvent(true) 则是要求父控件不对分发事件进行拦截,子控件自行消费触摸事件

#### 手势

 1. 创建GestureDetector对象,创建OnGestureListener的实现类
 2. 将ViewFlipper的触摸事件交由手势的触摸事件处理,重写onTouchEvent方法

```
return super.onTouchEvent(event);
```
改为
```
(GestureDetector对象).onTouchEvent(event);
return true;
```

***

#### 动画

```
 // 图片从右侧滑入
        rightInAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        rightInAnim.setDuration(1000);

        // 图片从左侧滑出
        leftOutAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        leftOutAnim.setDuration(1000);

        // 图片从右侧滑出
        rightOutAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        rightOutAnim.setDuration(1000);

        // 图片从左侧滑入
        leftInAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        leftInAnim.setDuration(1000);

        // 正常情况左侧划出，右侧滑入
        viewFlipper.setOutAnimation(leftOutAnim);
        viewFlipper.setInAnimation(rightInAnim);
```

#### onTouchEvent

```
@Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            // 当手指按下时，停止图片的循环播放。并记录当前x坐标
                viewFlipper.stopFlipping();
                currentX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (event.getX() - currentX > 100) {
	                // 手指向右滑动，左侧滑入，右侧滑出
                    viewFlipper.setInAnimation(leftInAnim);
                    viewFlipper.setOutAnimation(rightOutAnim);
                    viewFlipper.showPrevious();
                    viewFlipper.startFlipping();
                } else if (currentX - event.getX() > 100) {
	                // 手指向左滑动，右侧滑入，左侧滑出
                    viewFlipper.setInAnimation(rightInAnim);
                    viewFlipper.setOutAnimation(leftOutAnim);
                    viewFlipper.showNext();
                    viewFlipper.startFlipping();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
```

#### **注意:** 实现手势监听器如果有需要必须判断触摸事件发生的位置,不要发生触摸其他位置也会使图片滑动的情况

***

#### 自定义的ViewPager控件,大家可以上网搜搜,开源代码很多的

>引用: 作者风过后,https://my.oschina.net/u/579493/blog/496016