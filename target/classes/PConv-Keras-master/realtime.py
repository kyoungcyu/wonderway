import cv2
import numpy as np
import os
import random
import secrets
import string


from Sketcher import Sketcher
from libs.util import MaskGenerator, ImageChunker
from libs.pconv_model import PConvUnet

import sys
from copy import deepcopy

print('load model...')
model = PConvUnet(vgg_weights=None, inference_only=True)
model.load('pconv_imagenet.h5', train_bn=False)
# model.summary()

img = cv2.imread(sys.argv[1], cv2.IMREAD_COLOR)

img_masked = img.copy()
mask = np.zeros(img.shape[:2], np.uint8)

sketcher = Sketcher('image', [img_masked, mask], lambda : ((255, 255, 255), 255))
chunker = ImageChunker(512, 512, 30)

#추가한코드 시작
download_folder = os.path.join(os.path.expanduser('~'), 'Downloads')

def generate_random_string(length):
    letters = string.ascii_letters + string.digits
    return ''.join(secrets.choice(letters) for _ in range(length))
#추가한코드 종료    
    

while True:
  key = cv2.waitKey()

  if key == ord('q'): # quit
    break
  if key == ord('r'): # reset
    print('reset')
    img_masked[:] = img
    mask[:] = 0
    sketcher.show()
  if key == 32: # hit spacebar to run inpainting
    input_img = img_masked.copy()
    input_img = input_img.astype(np.float32) / 255.

    input_mask = cv2.bitwise_not(mask)
    input_mask = input_mask.astype(np.float32) / 255.
    input_mask = np.repeat(np.expand_dims(input_mask, axis=-1), repeats=3, axis=-1)

    # cv2.imshow('input_img', input_img)
    # cv2.imshow('input_mask', input_mask)

    print('processing...')

    chunked_imgs = chunker.dimension_preprocess(deepcopy(input_img))
    chunked_masks = chunker.dimension_preprocess(deepcopy(input_mask))

    # for i, im in enumerate(chunked_imgs):
    #   cv2.imshow('im %s' % i, im)
    #   cv2.imshow('mk %s' % i, chunked_masks[i])

    pred_imgs = model.predict([chunked_imgs, chunked_masks])
    result_img = chunker.dimension_postprocess(pred_imgs, input_img)

    print('completed!')

    cv2.imshow('result', result_img)
    
    
    #추가한코드
    random_filename = generate_random_string(8)
    download_filename=f"wonderway_aiphoto_{random_filename}.jpg"
    image_path = os.path.join(download_folder, download_filename)
    result_img = (result_img * 255).astype(np.uint8)
    cv2.imwrite(image_path, result_img)
    print(f"Image saved: {image_path}")
    #추가한코드 종료

cv2.destroyAllWindows()
