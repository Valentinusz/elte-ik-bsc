import cv2
from threading import Thread, Lock


class VideoGrabber(Thread):
		"""A threaded video grabber.

		Attributes:
		encode_params ():
		cap (str):
		attr2 (:obj:`int`, optional): Description of `attr2`.

		"""
		def __init__(self, jpeg_quality):
				"""Constructor.

				Args:
				jpeg_quality (:obj:`int`): Quality of JPEG encoding, in 0, 100.

				"""
				Thread.__init__(self)
				self.encode_param = [int(cv2.IMWRITE_JPEG_QUALITY), jpeg_quality]
				self.init()
				
		def init(self):		
				# self.cap = cv2.VideoCapture(0)
				self.cap = cv2.VideoCapture("video.mp4")
				self.running = True
				self.buffer = None
				self.lock = Lock()
				self.quit = False
		

		def stop(self):
			self.running = False
			self.quit = True
				
		def ret(self):
			self.lock.acquire()
			self.running = False
			self.lock.release()

		def get_buffer(self):
				"""Method to access the encoded buffer.

				Returns:
				np.ndarray: the compressed image if one has been acquired. None otherwise.
				"""
				if self.buffer is not None:
						self.lock.acquire()
						cpy = self.buffer.copy()
						self.lock.release()
						return cpy

		def run(self):
				while not self.quit:
					self.init()
					while self.running:
							success, img = self.cap.read()
							if not success:
									continue

							# JPEG compression
							# Protected by a lock
							# As the main thread may asks to access the buffer
							self.lock.acquire()
							result, self.buffer = cv2.imencode('.jpg', img, self.encode_param)
							self.lock.release()
						