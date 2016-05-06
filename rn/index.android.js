'use strict';

// var MOVIE_LIST = [{title:"《美国队长3》",desc:"绝对给力的漫威动画片",thumbnial:"http://i.imgur.com/UePbdph.jpg"}]
var REQUEST_URL = 'https://raw.githubusercontent.com/facebook/react-native/master/docs/MoviesExample.json';

var ToastAndroid2 = require('./ToastAndroid').ToastAndroid2;
var LoginModule = require('./ToastAndroid').LoginModule;
var CusImageView = require('./CustomImageView');

import React, { Component } from 'react';

import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Image,
  ListView,
  TouchableHighlight,
  TextInput,
  DeviceEventEmitter,
} from 'react-native';

class ReactNativeProject extends React.Component {
  constructor(props) {
    super(props);
  
    this.state = {
      movies : null,
      dataSource: new ListView.DataSource({
        rowHasChanged: (row1, row2) => row1 !== row2,
      }),      
      loaded : false ,
      nameInput : "",
      pwdInput : "",
    };
  }

  componentDidMount(){
    // this.fetchData();
    DeviceEventEmitter.addListener("Js_Event",(msg) => {
      // ToastAndroid2.show(msg.toString(),ToastAndroid2.short);
      // console.log(msg.key);
      ToastAndroid2.show(msg.key+"呵呵呵",ToastAndroid2.short);
    });
  }

  fetchData() {
    fetch(REQUEST_URL).then((response) => response.json()).then((responseData) => {
        this.setState({
          dataSource: this.state.dataSource.cloneWithRows(responseData.movies),
          loaded: true,        
        });
      }).done();
  }

  customView() {
    return(
        <View style={styles.container}>
          <CusImageView style={{width:200,height:200,backgroundColor:'red'}}
            imageToast="呵呵呵"
            source={{uri:'http://f.hiphotos.bdimg.com/imgad/pic/item/77c6a7efce1b9d1608043b8cf6deb48f8d546418.jpg'}}
          />
        </View>
      );
  }

  render() {
   return this.customView();
     // return this.onPressView();
    // if (!this.state.loaded) {
    //   return this.renderingView();
    // }

    // var movie = MOVIE_LIST[0];
    // var movie = this.state.movies[0];

    // return (
    //   <View style={styles.container}>
    //     <ListView dataSource={this.state.dataSource} style={styles.listView}
    //       renderRow={this.renderMovie}
    //     />
    //   </View>
    //   );

    // return (
    //   <View style={styles.container}>
    //     <Text style={styles.hello}>Hello, World</Text>
    //     <Text>测试ReactNative</Text>
    //     <View style={styles.view_horizontal}>
    //       <Image style={styles.image_style} source={{uri: movie.posters.thumbnail}}/>
    //       <Text style={styles.textDetail}>
    //           {movie.title}
    //       </Text>
    //     </View>
    //   </View>
    // )
  }

  onPressView() {
    return(
        <View style={styles.container}>
          <TouchableHighlight onPress={this.onPressBtn}>
          <Text style={styles.textDetail}>
            你点我啊
          </Text>
          </TouchableHighlight>

          <TextInput style={{height:40,borderColor:'grey',borderWidth:1}} 
            onChangeText={(text) => {
              this.setState({nameInput:text})
            }}
            value={this.state.nameInput}
            placeholder="请输入姓名"
            autoFocus={true}
          />
          <TextInput style={{height:40,borderColor:'grey',borderWidth:1}}
            onChangeText={(text) => {
              this.setState({pwdInput:text})
            }}
            value={this.state.pwdInput}
            placeholder="请输入密码"
            autoFocus={true}
          />
          <TouchableHighlight onPress={this.onLoginSystem.bind(this)}
            style={{marginTop:20}}
          >
            <Text style={{color:'green',textAlign:'center'}}>
              登录系统
            </Text>
          </TouchableHighlight>            
        </View>
      );
  }

  onPressBtn (){
    ToastAndroid2.show("你好啊",ToastAndroid2.short);
  }

  onLoginSystem (){
    var name = this.state.nameInput;
    var pwd  = this.state.pwdInput;
    LoginModule.loginSystem(name,pwd,success => {
      ToastAndroid2.show(success,ToastAndroid2.short);
    },error => {
      ToastAndroid2.show(error,ToastAndroid2.short);
    });
  }

  renderingView() {
    return (
        <View style={styles.container}>
          <Text>
            正在加载电影数据...
          </Text>
        </View>
      );
  }

  renderMovie(movie){
    return(
        <View style={styles.view_horizontal}>
          <Image style={styles.image_style} source={{uri: movie.posters.thumbnail}}/>
          <Text style={styles.textDetail}>
              {movie.title}
          </Text>
        </View>
      );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  view_horizontal:{
    flexDirection: 'row'
  },
  image_style:{
    width: 30,
    height: 30
  },
  textDetail: {
    fontSize: 15,
    color: 'red',
    width:100,
    height:50,
    justifyContent: 'center',
    textAlign: 'center'
  },
  listView: {
    paddingTop : 20,
    backgroundColor : '#F5FCFF'
  }
});
AppRegistry.registerComponent('ReactNativeProject', () => ReactNativeProject);