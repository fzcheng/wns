﻿$axure.loadCurrentPage({
  "url":"detail_3.html",
  "generationDate":new Date(1398849772736.4),
  "isCanvasEnabled":false,
  "variables":["OnLoadVariable"],
  "defaultBackgroundImageInfo":{
    "path":"images/home/bg.png",
    "width":640,
    "height":240},
  "viewIdToBackgroundImageInfo":{
},
  "page":{
    "packageId":"ab95659f535d460c975358a9aac59ed9",
    "type":"Axure:Page",
    "name":"detail 3",
    "notes":{
},
    "style":{
      "baseStyle":"627587b6038d43cca051c114ac41ad32",
      "pageAlignment":"near",
      "fill":{
        "fillType":"solid",
        "color":0xFFFFFFFF},
      "image":null,
      "imageHorizontalAlignment":"near",
      "imageVerticalAlignment":"near",
      "imageRepeat":"auto",
      "favicon":null,
      "sketchFactor":"0",
      "colorStyle":"appliedColor",
      "fontName":"应用字体",
      "borderWidth":"0"},
    "adaptiveStyles":{
},
    "interactionMap":{
},
    "diagram":{
      "objects":[{
          "id":"0db33eff76b44291b0f84f371a4d2cb7",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "size":{
              "width":640,
              "height":81}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"46692e561a554ee68c05e95e7c1ca74e",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "size":{
                  "width":640,
                  "height":81}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"images/detail_1/u0.png"}},
{
          "id":"f0ff604f546345c8b4f3f8dc14b9258c",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "location":{
              "x":0,
              "y":81},
            "size":{
              "width":640,
              "height":640}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"3af16566d6b94526a52eb0412acf36d3",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":0,
                  "y":81},
                "size":{
                  "width":640,
                  "height":640}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"images/detail_1/u2.png"}},
{
          "id":"6a99aa2dd60645eaa4f80cb84ed1c090",
          "label":"DT",
          "type":"dynamicPanel",
          "styleType":"dynamicPanel",
          "visible":true,
          "style":{
            "location":{
              "x":80,
              "y":100},
            "size":{
              "width":480,
              "height":289}},
          "adaptiveStyles":{
},
          "interactionMap":{
            "onShow":{
              "description":"OnShow",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT1 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":1,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT2 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":2,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT3 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":3,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT4 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":4,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"fadeWidget",
                      "description":"隐藏 DT",
                      "objectsToFades":[{
                          "objectPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "fadeInfo":{
                            "fadeType":"hide",
                            "options":{
                              "showType":"none"}}}]}]}]},
            "onHide":{
              "description":"OnHide",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"fadeWidget",
                      "description":"显示 DT",
                      "objectsToFades":[{
                          "objectPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "fadeInfo":{
                            "fadeType":"show",
                            "options":{
                              "showType":"none"}}}]}]}]},
            "onLoad":{
              "description":"OnLoad",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT1 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":1,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT2 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":2,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT3 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":3,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"setPanelState",
                      "description":"设置 DT 到 DT4 淡入淡出 in 500ms 显示隐藏",
                      "panelsToStates":[{
                          "panelPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "stateInfo":{
                            "setStateType":"diagram",
                            "stateNumber":4,
                            "stateValue":{
                              "exprType":"stringLiteral",
                              "value":"1",
                              "stos":[]},
                            "loop":false,
                            "showWhenSet":true,
                            "options":{
                              "animateIn":{
                                "easing":"fade",
                                "duration":500},
                              "compress":false}}}]},
{
                      "action":"wait",
                      "description":"等待2000毫秒",
                      "waitTime":2000},
{
                      "action":"fadeWidget",
                      "description":"隐藏 DT",
                      "objectsToFades":[{
                          "objectPath":["6a99aa2dd60645eaa4f80cb84ed1c090"],
                          "fadeInfo":{
                            "fadeType":"hide",
                            "options":{
                              "showType":"none"}}}]}]}]}},
          "scrollbars":"none",
          "fitToContent":true,
          "propagate":false,
          "diagrams":[{
              "id":"13002694755145738c446267b4ce4f77",
              "label":"DT1",
              "type":"Axure:PanelDiagram",
              "objects":[{
                  "id":"c22dfc73d9a145ad80f409084d0c2e10",
                  "label":"",
                  "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                  "panelIndex":0,
                  "type":"imageBox",
                  "styleType":"imageBox",
                  "visible":true,
                  "style":{
                    "size":{
                      "width":480,
                      "height":289}},
                  "adaptiveStyles":{
},
                  "objects":[{
                      "id":"d00cdff25d164de486d944a6900eb2b1",
                      "label":"",
                      "isContained":true,
                      "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                      "panelIndex":0,
                      "type":"richTextPanel",
                      "styleType":"paragraph",
                      "visible":true,
                      "style":{
                        "size":{
                          "width":480,
                          "height":289}},
                      "adaptiveStyles":{
}}],
                  "images":{
                    "normal~":"images/detail_3/u5.jpg"}}],
              "style":{
                "fill":{
                  "fillType":"solid",
                  "color":0xFFFFFF},
                "image":null,
                "imageHorizontalAlignment":"near",
                "imageVerticalAlignment":"near",
                "imageRepeat":"auto"},
              "adaptiveStyles":{
}},
{
              "id":"becdecf68e0f46a6b44627535c6ac010",
              "label":"DT2",
              "type":"Axure:PanelDiagram",
              "objects":[{
                  "id":"37cdccf4604f4345b367468e7f8a7907",
                  "label":"",
                  "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                  "panelIndex":1,
                  "type":"imageBox",
                  "styleType":"imageBox",
                  "visible":true,
                  "style":{
                    "size":{
                      "width":480,
                      "height":289}},
                  "adaptiveStyles":{
},
                  "objects":[{
                      "id":"87cb3ec587f94d54a68956c2004dc963",
                      "label":"",
                      "isContained":true,
                      "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                      "panelIndex":1,
                      "type":"richTextPanel",
                      "styleType":"paragraph",
                      "visible":true,
                      "style":{
                        "size":{
                          "width":480,
                          "height":289}},
                      "adaptiveStyles":{
}}],
                  "images":{
                    "normal~":"images/detail_3/u7.jpg"}}],
              "style":{
                "fill":{
                  "fillType":"solid",
                  "color":0xFFFFFF},
                "image":null,
                "imageHorizontalAlignment":"near",
                "imageVerticalAlignment":"near",
                "imageRepeat":"auto"},
              "adaptiveStyles":{
}},
{
              "id":"ce1011f56bd74553abdb45963a6cfe8c",
              "label":"DT3",
              "type":"Axure:PanelDiagram",
              "objects":[{
                  "id":"047a4fa5b0ef4a88bbdb08c3eeec2e06",
                  "label":"",
                  "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                  "panelIndex":2,
                  "type":"imageBox",
                  "styleType":"imageBox",
                  "visible":true,
                  "style":{
                    "size":{
                      "width":480,
                      "height":289}},
                  "adaptiveStyles":{
},
                  "objects":[{
                      "id":"20d432aef9e14b93809cf2dd5e66572a",
                      "label":"",
                      "isContained":true,
                      "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                      "panelIndex":2,
                      "type":"richTextPanel",
                      "styleType":"paragraph",
                      "visible":true,
                      "style":{
                        "size":{
                          "width":480,
                          "height":289}},
                      "adaptiveStyles":{
}}],
                  "images":{
                    "normal~":"images/detail_3/u9.jpg"}}],
              "style":{
                "fill":{
                  "fillType":"solid",
                  "color":0xFFFFFF},
                "image":null,
                "imageHorizontalAlignment":"near",
                "imageVerticalAlignment":"near",
                "imageRepeat":"auto"},
              "adaptiveStyles":{
}},
{
              "id":"59adead548044d8ea7364d22d90e9f7f",
              "label":"DT4",
              "type":"Axure:PanelDiagram",
              "objects":[{
                  "id":"6adef8911d094cb6bfc29d92a2dc6262",
                  "label":"",
                  "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                  "panelIndex":3,
                  "type":"imageBox",
                  "styleType":"imageBox",
                  "visible":true,
                  "style":{
                    "size":{
                      "width":480,
                      "height":289}},
                  "adaptiveStyles":{
},
                  "objects":[{
                      "id":"639674e88a974321ac05b18b818471c6",
                      "label":"",
                      "isContained":true,
                      "parentDynamicPanel":"6a99aa2dd60645eaa4f80cb84ed1c090",
                      "panelIndex":3,
                      "type":"richTextPanel",
                      "styleType":"paragraph",
                      "visible":true,
                      "style":{
                        "size":{
                          "width":480,
                          "height":289}},
                      "adaptiveStyles":{
}}],
                  "images":{
                    "normal~":"images/detail_3/u11.jpg"}}],
              "style":{
                "fill":{
                  "fillType":"solid",
                  "color":0xFFFFFF},
                "image":null,
                "imageHorizontalAlignment":"near",
                "imageVerticalAlignment":"near",
                "imageRepeat":"auto"},
              "adaptiveStyles":{
}}]},
{
          "id":"ad6445eef7da45009347522d50b2cd63",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "location":{
              "x":80,
              "y":419},
            "size":{
              "width":100,
              "height":100}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"cc10fd9ae8b34e8f83f39f179af92f7e",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":80,
                  "y":419},
                "size":{
                  "width":100,
                  "height":100}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"images/detail_3/u13.png"}},
{
          "id":"7f3d1fc8cbde486ba71854d443317b90",
          "label":"",
          "type":"horizontalLine",
          "styleType":"horizontalLine",
          "visible":true,
          "style":{
            "location":{
              "x":37,
              "y":399},
            "size":{
              "width":563,
              "height":10},
            "borderWidth":"2",
            "borderFill":{
              "fillType":"solid",
              "color":0xFF999999},
            "linePattern":"dotted"},
          "adaptiveStyles":{
},
          "images":{
            "start~":"resources/images/transparent.gif",
            "end~":"resources/images/transparent.gif",
            "line~":"images/detail_1/u15_line.png"}},
{
          "id":"7ce295e63cf1497697a5661cf8a1ddc6",
          "label":"",
          "type":"buttonShape",
          "styleType":"paragraph",
          "visible":true,
          "style":{
            "location":{
              "x":210,
              "y":423},
            "size":{
              "width":200,
              "height":96}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"e6d84613422e41c3a4afdc1ec771e304",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":210,
                  "y":423},
                "size":{
                  "width":200,
                  "height":96}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"resources/images/transparent.gif"}},
{
          "id":"001eb7dc6fd14fadb02d5947e30b0b66",
          "label":"",
          "type":"buttonShape",
          "styleType":"paragraph",
          "visible":true,
          "style":{
            "location":{
              "x":80,
              "y":529},
            "size":{
              "width":510,
              "height":96}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"9499f43616774c13a5c6fece1c5945ff",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":80,
                  "y":529},
                "size":{
                  "width":510,
                  "height":96}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"resources/images/transparent.gif"}},
{
          "id":"b2988926bd7a423ab447a354f6539d45",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "location":{
              "x":51,
              "y":661},
            "size":{
              "width":165,
              "height":60}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"e00979c98f964e9fbf6b4e47549daf44",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":51,
                  "y":661},
                "size":{
                  "width":165,
                  "height":60}},
              "adaptiveStyles":{
}}],
          "interactionMap":{
            "onClick":{
              "description":"OnClick",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"linkWindow",
                      "description":"在 当前窗口 打开 http://feimiao.magicbirds.cn/wnsv2/d.do?file=kxxxlyz.apk",
                      "target":{
                        "targetType":"webUrl",
                        "urlLiteral":{
                          "exprType":"stringLiteral",
                          "value":"http://feimiao.magicbirds.cn/wnsv2/d.do?file=kxxxlyz.apk",
                          "stos":[]},
                        "includeVariables":false},
                      "linkType":"current"}]}]}},
          "tabbable":true,
          "images":{
            "normal~":"images/home/u12.png"}},
{
          "id":"44609704e26a48c5848f97050510af39",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "location":{
              "x":0,
              "y":721},
            "size":{
              "width":640,
              "height":60}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"9e9d40683d5e41fa8c189afcaa23025e",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":0,
                  "y":721},
                "size":{
                  "width":640,
                  "height":60}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"images/home/u34.png"}},
{
          "id":"4584d77e096943d1ba70bb9eb7c8db9b",
          "label":"",
          "type":"flowShape",
          "styleType":"flowShape",
          "visible":true,
          "style":{
            "fontSize":"18px",
            "fill":{
              "fillType":"solid",
              "color":0xFFFFFF},
            "location":{
              "x":32,
              "y":750},
            "size":{
              "width":577,
              "height":25},
            "borderFill":{
              "fillType":"solid",
              "color":0xFFFFFF}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"d12aac77b36a4f99ab38b1070371c46d",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "fontSize":"18px",
                "fill":{
                  "fillType":"solid",
                  "color":0xFFFFFF},
                "location":{
                  "x":32,
                  "y":750},
                "size":{
                  "width":577,
                  "height":25},
                "borderFill":{
                  "fillType":"solid",
                  "color":0xFFFFFF}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"resources/images/transparent.gif"}},
{
          "id":"8a46d96c3fb541e9826bb4c3532677b1",
          "label":"",
          "type":"buttonShape",
          "styleType":"h1",
          "visible":true,
          "style":{
            "fontName":"'Arial Normal', 'Arial'",
            "fontSize":"48px",
            "fontWeight":"400",
            "foreGroundFill":{
              "fillType":"solid",
              "color":0xFFFFFFFF,
              "opacity":1},
            "location":{
              "x":209,
              "y":13},
            "size":{
              "width":241,
              "height":57}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"68574c455b234ed184e2015ba973727f",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "fontName":"'Arial Normal', 'Arial'",
                "fontSize":"48px",
                "fontWeight":"400",
                "foreGroundFill":{
                  "fillType":"solid",
                  "color":0xFFFFFFFF,
                  "opacity":1},
                "location":{
                  "x":209,
                  "y":13},
                "size":{
                  "width":241,
                  "height":57}},
              "adaptiveStyles":{
}}],
          "images":{
            "normal~":"resources/images/transparent.gif"}},
{
          "id":"5fabba8b4ecc4b95a99ea93ddd41c9da",
          "label":"button",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "stateStyles":{
              "mouseDown":{
                "outerShadow":{
                  "on":false,
                  "offsetX":2,
                  "offsetY":2,
                  "blurRadius":5,
                  "color":{
                    "r":0,
                    "g":0,
                    "b":0,
                    "a":0.349019607843137}}}},
            "location":{
              "x":16,
              "y":10},
            "size":{
              "width":74,
              "height":60},
            "outerShadow":{
              "on":true,
              "offsetX":2,
              "offsetY":2,
              "blurRadius":5,
              "color":{
                "r":0,
                "g":0,
                "b":0,
                "a":0.349019607843137}}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"be4a1178db2f4d75b4a1d0a52076f861",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "stateStyles":{
                  "mouseDown":{
                    "outerShadow":{
                      "on":false,
                      "offsetX":2,
                      "offsetY":2,
                      "blurRadius":5,
                      "color":{
                        "r":0,
                        "g":0,
                        "b":0,
                        "a":0.349019607843137}}}},
                "location":{
                  "x":16,
                  "y":10},
                "size":{
                  "width":74,
                  "height":60},
                "outerShadow":{
                  "on":true,
                  "offsetX":2,
                  "offsetY":2,
                  "blurRadius":5,
                  "color":{
                    "r":0,
                    "g":0,
                    "b":0,
                    "a":0.349019607843137}}},
              "adaptiveStyles":{
}}],
          "interactionMap":{
            "onClick":{
              "description":"OnClick",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"linkWindow",
                      "description":"在 当前窗口 打开 Home",
                      "target":{
                        "targetType":"page",
                        "url":"home.html",
                        "includeVariables":true},
                      "linkType":"current"}]}]}},
          "tabbable":true,
          "images":{
            "normal~":"images/detail_1/button_u24.png",
            "mouseDown~":"images/detail_1/button_u24_mouseDown.png"}},
{
          "id":"2b86427ada1c4e0db50cf985726c498b",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "location":{
              "x":238,
              "y":661},
            "size":{
              "width":165,
              "height":60}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"bd765e628c6042d491d1cbcf47f46cd5",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":238,
                  "y":661},
                "size":{
                  "width":165,
                  "height":60}},
              "adaptiveStyles":{
}}],
          "interactionMap":{
            "onClick":{
              "description":"OnClick",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"linkWindow",
                      "description":"在 当前窗口 打开 http://feimiao.magicbirds.cn/wnsv2/d.do?file=xxl_wo.apk",
                      "target":{
                        "targetType":"webUrl",
                        "urlLiteral":{
                          "exprType":"stringLiteral",
                          "value":"http://feimiao.magicbirds.cn/wnsv2/d.do?file=xxl_wo.apk",
                          "stos":[]},
                        "includeVariables":false},
                      "linkType":"current"}]}]}},
          "tabbable":true,
          "images":{
            "normal~":"images/home/u76.png"}},
{
          "id":"d9c59f8aa0bf46e696196e2341448fc9",
          "label":"",
          "type":"imageBox",
          "styleType":"imageBox",
          "visible":true,
          "style":{
            "location":{
              "x":425,
              "y":661},
            "size":{
              "width":165,
              "height":60}},
          "adaptiveStyles":{
},
          "objects":[{
              "id":"2b1e558aa6be49bca738b68cd6044851",
              "label":"",
              "isContained":true,
              "type":"richTextPanel",
              "styleType":"paragraph",
              "visible":true,
              "style":{
                "location":{
                  "x":425,
                  "y":661},
                "size":{
                  "width":165,
                  "height":60}},
              "adaptiveStyles":{
}}],
          "interactionMap":{
            "onClick":{
              "description":"OnClick",
              "cases":[{
                  "description":"用例 1",
                  "isNewIfGroup":false,
                  "actions":[{
                      "action":"linkWindow",
                      "description":"在 当前窗口 打开 http://feimiao.magicbirds.cn/wnsv2/d.do?file=xxl_ai.apk",
                      "target":{
                        "targetType":"webUrl",
                        "urlLiteral":{
                          "exprType":"stringLiteral",
                          "value":"http://feimiao.magicbirds.cn/wnsv2/d.do?file=xxl_ai.apk",
                          "stos":[]},
                        "includeVariables":false},
                      "linkType":"current"}]}]}},
          "tabbable":true,
          "images":{
            "normal~":"images/home/u74.png"}}]}},
  "masters":{
},
  "objectPaths":{
    "0db33eff76b44291b0f84f371a4d2cb7":{
      "scriptId":"u0"},
    "46692e561a554ee68c05e95e7c1ca74e":{
      "scriptId":"u1"},
    "f0ff604f546345c8b4f3f8dc14b9258c":{
      "scriptId":"u2"},
    "3af16566d6b94526a52eb0412acf36d3":{
      "scriptId":"u3"},
    "6a99aa2dd60645eaa4f80cb84ed1c090":{
      "scriptId":"u4"},
    "c22dfc73d9a145ad80f409084d0c2e10":{
      "scriptId":"u5"},
    "d00cdff25d164de486d944a6900eb2b1":{
      "scriptId":"u6"},
    "37cdccf4604f4345b367468e7f8a7907":{
      "scriptId":"u7"},
    "87cb3ec587f94d54a68956c2004dc963":{
      "scriptId":"u8"},
    "047a4fa5b0ef4a88bbdb08c3eeec2e06":{
      "scriptId":"u9"},
    "20d432aef9e14b93809cf2dd5e66572a":{
      "scriptId":"u10"},
    "6adef8911d094cb6bfc29d92a2dc6262":{
      "scriptId":"u11"},
    "639674e88a974321ac05b18b818471c6":{
      "scriptId":"u12"},
    "ad6445eef7da45009347522d50b2cd63":{
      "scriptId":"u13"},
    "cc10fd9ae8b34e8f83f39f179af92f7e":{
      "scriptId":"u14"},
    "7f3d1fc8cbde486ba71854d443317b90":{
      "scriptId":"u15"},
    "7ce295e63cf1497697a5661cf8a1ddc6":{
      "scriptId":"u16"},
    "e6d84613422e41c3a4afdc1ec771e304":{
      "scriptId":"u17"},
    "001eb7dc6fd14fadb02d5947e30b0b66":{
      "scriptId":"u18"},
    "9499f43616774c13a5c6fece1c5945ff":{
      "scriptId":"u19"},
    "b2988926bd7a423ab447a354f6539d45":{
      "scriptId":"u20"},
    "e00979c98f964e9fbf6b4e47549daf44":{
      "scriptId":"u21"},
    "44609704e26a48c5848f97050510af39":{
      "scriptId":"u22"},
    "9e9d40683d5e41fa8c189afcaa23025e":{
      "scriptId":"u23"},
    "4584d77e096943d1ba70bb9eb7c8db9b":{
      "scriptId":"u24"},
    "d12aac77b36a4f99ab38b1070371c46d":{
      "scriptId":"u25"},
    "8a46d96c3fb541e9826bb4c3532677b1":{
      "scriptId":"u26"},
    "68574c455b234ed184e2015ba973727f":{
      "scriptId":"u27"},
    "5fabba8b4ecc4b95a99ea93ddd41c9da":{
      "scriptId":"u28"},
    "be4a1178db2f4d75b4a1d0a52076f861":{
      "scriptId":"u29"},
    "2b86427ada1c4e0db50cf985726c498b":{
      "scriptId":"u30"},
    "bd765e628c6042d491d1cbcf47f46cd5":{
      "scriptId":"u31"},
    "d9c59f8aa0bf46e696196e2341448fc9":{
      "scriptId":"u32"},
    "2b1e558aa6be49bca738b68cd6044851":{
      "scriptId":"u33"}}});